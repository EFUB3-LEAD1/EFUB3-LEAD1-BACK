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
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> findMemberInfoById() {
        return ResponseEntity.ok(memberService.findMemberInfoById(SecurityUtil.getCurrentMemberId()));
    }

    @GetMapping("/{account}")
    public ResponseEntity<MemberResponseDto> findMemberInfoByAccount(@PathVariable String account) {
        return ResponseEntity.ok(memberService.findMemberInfoByAccount(account));
    }
}