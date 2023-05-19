package efub.clone.hanatour.domain.heart.controller;

import efub.clone.hanatour.domain.heart.dto.HeartRemoveRequestDto;
import efub.clone.hanatour.domain.heart.dto.HeartRequestDto;
import efub.clone.hanatour.domain.heart.service.HeartService;
import efub.clone.hanatour.domain.member.util.SecurityUtil;
import efub.clone.hanatour.domain.tour.dto.TourInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hearts")
@RequiredArgsConstructor
@Tag(name = "Tour 좋아요 API", description = "Tour에 대한 좋아요 기능 API입니다.")
public class HeartController {
    private final HeartService heartService;

    // Tour 좋아요
    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Tour에 대한 좋아요를 등록하는 API입니다.", description = "JWT 토큰과 tourId가 필요합니다.")
    public String createHeart(@RequestHeader("Authorization") String token, @RequestBody HeartRequestDto dto) {
        heartService.createHeart(token, dto.getTourId());
        return "좋아요 처리 되었습니다.";
    }


    // Tour 좋아요 삭제
    @DeleteMapping()
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Tour에 대한 좋아요를 삭제하는 API입니다.", description = "JWT 토큰과 tourId가 필요합니다.")
    public String deleteHeart(@RequestHeader("Authorization") String token, @RequestBody HeartRemoveRequestDto dto) {
        heartService.deleteHeart(token, dto.getHeartId());
        return "좋아요를 취소했습니다.";
    }

    // 사용자 별로 좋아요한 Tour 목록 조회
    @GetMapping("/")
    @Operation(summary = "사용자의 좋아요 목록을 반환합니다.", description = "JWT 토큰이 필요합니다.")
    @ResponseStatus(HttpStatus.OK)
    public List<TourInfoDto> getHeartToursByMember() {
        String account = SecurityUtil.getCurrentMemberAccount();
        return heartService.getHeartTours(account);
    }
}
