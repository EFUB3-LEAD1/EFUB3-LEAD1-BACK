package efub.clone.hanatour.domain.member.domain.controller;

import efub.clone.hanatour.domain.member.domain.dto.MemberRequestDto;
import efub.clone.hanatour.domain.member.domain.dto.MemberResponseDto;
import efub.clone.hanatour.domain.member.domain.dto.TokenDto;
import efub.clone.hanatour.domain.member.domain.dto.TokenRequestDto;
import efub.clone.hanatour.domain.member.domain.service.AuthService;
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

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.login(memberRequestDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }

    @PostMapping("/sign")
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
