package efub.clone.hanatour.domain.member.controller;

import efub.clone.hanatour.domain.member.dto.MemberRequestDto;
import efub.clone.hanatour.domain.member.dto.MemberResponseDto;
import efub.clone.hanatour.domain.member.dto.TokenDto;
import efub.clone.hanatour.domain.member.dto.TokenRequestDto;
import efub.clone.hanatour.domain.member.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "사용자 인증이 이루어지는 API입니다.", description = "JWT 토큰을 반환합니다.")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "로그인 API입니다. Jwt를 발급합니다.")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.login(memberRequestDto));
    }

    @PostMapping("/reissue")
    @Operation(summary = "refresh 토큰 사용을 위한 API입니다. 개발하지 않으셔도 무방합니다.")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }

    @PostMapping("/sign")
    @Operation(summary = "테스트를 위해 개발한, 사용하지 않는 API입니다.")
    public ResponseEntity<MemberResponseDto> signUp(@RequestBody MemberRequestDto memberRequestDto){
        return ResponseEntity.ok(new MemberResponseDto(authService.createMember(memberRequestDto).getAccount()));
    }

    @DeleteMapping("/logout")
    public HttpStatus logout(
            @RequestBody @Valid TokenRequestDto tokenRequestDto) {
        authService.logout(tokenRequestDto);
        return HttpStatus.OK;
    }
}
