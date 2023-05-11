package efub.clone.hanatour.domain.member.domain.service;

import efub.clone.hanatour.domain.member.domain.dto.MemberRequestDto;
import efub.clone.hanatour.domain.member.domain.dto.TokenDto;
import efub.clone.hanatour.domain.member.domain.dto.TokenRequestDto;
import efub.clone.hanatour.domain.member.domain.entity.RefreshToken;
import efub.clone.hanatour.domain.member.domain.repository.MemberRepository;
import efub.clone.hanatour.domain.member.domain.repository.RefreshTokenRepository;
import efub.clone.hanatour.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RedisTemplate redisTemplate;

    @Transactional
    public TokenDto login(MemberRequestDto memberRequestDto) {
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = memberRequestDto.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        redisTemplate.opsForValue().set("RT: " + memberRequestDto.getAccount(), tokenDto.getRefreshToken(),tokenDto.getAccessTokenExpiresIn(), TimeUnit.MILLISECONDS);

        // 5. 토큰 발급
        return tokenDto;
    }

    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }

    @Transactional
    public void logout(TokenRequestDto tokenRequestDto){
        // 1. 로그아웃 하고 싶은 토큰이 유효한지 먼저 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getAccessToken())){
            throw new IllegalArgumentException("로그아웃 : 유효하지 않은 토큰입니다.");
        }

        // 2. Access Token에서 권한 정보 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // Redis에서 Refresh Token 이 있는지 여부를 확인, token 존재할 경우 삭제
        if (redisTemplate.opsForValue().get("RT:"+authentication.getName())!=null){
            // Refresh Token 삭제
            redisTemplate.delete("RT:"+authentication.getName());
        }

        // 해당 Access Token 유효시간을 가지고 와서 BlackList에 저장
        Long expiration = tokenProvider.getExpiration(tokenRequestDto.getAccessToken());
        redisTemplate.opsForValue().set(tokenRequestDto.getAccessToken(),"logout",expiration,TimeUnit.MILLISECONDS);

    }
}

