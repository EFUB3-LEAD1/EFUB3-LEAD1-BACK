package efub.clone.hanatour.domain.member.domain.controller;

import efub.clone.hanatour.domain.member.domain.dto.MemberResponseDto;
import efub.clone.hanatour.domain.member.domain.service.MemberService;
import efub.clone.hanatour.domain.member.domain.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> findMemberInfoByEmail() {
        return ResponseEntity.ok(memberService.findMemberInfoByAccount(SecurityUtil.getCurrentMemberAccount()));
    }

}