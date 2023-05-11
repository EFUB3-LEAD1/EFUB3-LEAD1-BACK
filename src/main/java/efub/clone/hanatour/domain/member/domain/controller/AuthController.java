package efub.clone.hanatour.domain.member.domain.controller;

import efub.clone.hanatour.domain.member.domain.dto.MemberRequestDto;
import efub.clone.hanatour.domain.member.domain.dto.TokenDto;
import efub.clone.hanatour.domain.member.domain.dto.TokenRequestDto;
import efub.clone.hanatour.domain.member.domain.service.AuthService;
import efub.clone.hanatour.global.Util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final RedisUtil redisUtil;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.login(memberRequestDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }

    @DeleteMapping("/logout")
    public HttpStatus logout(
            @RequestBody @Valid TokenDto requestTokenDto) {
        authService.logout(requestTokenDto.getAccessToken(), requestTokenDto.getRefreshToken());
        return HttpStatus.OK;
    }
}
