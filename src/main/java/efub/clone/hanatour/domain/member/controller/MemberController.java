package efub.clone.hanatour.domain.member.controller;

import efub.clone.hanatour.domain.member.dto.MemberResponseDto;
import efub.clone.hanatour.domain.member.service.MemberService;
import efub.clone.hanatour.domain.member.util.SecurityUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Tag(name = "사용자 정보를 반환하는 API입니다.", description = "JWT 토큰을 입력하면, 사용자 정보를 반환합니다.")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> findMemberInfoByEmail() {
        return ResponseEntity.ok(memberService.findMemberInfoByAccount(SecurityUtil.getCurrentMemberAccount()));
    }

}