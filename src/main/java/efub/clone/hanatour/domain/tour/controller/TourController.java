package efub.clone.hanatour.domain.tour.controller;

import efub.clone.hanatour.domain.heart.dto.HeartRequestDto;
import efub.clone.hanatour.domain.heart.service.HeartService;
import efub.clone.hanatour.domain.member.domain.dto.MemberRequestDto;
import efub.clone.hanatour.domain.member.domain.dto.MemberResponseDto;
import efub.clone.hanatour.domain.member.domain.dto.TokenDto;
import efub.clone.hanatour.domain.member.domain.entity.Member;
import efub.clone.hanatour.domain.member.domain.util.SecurityUtil;
import efub.clone.hanatour.domain.tour.dto.*;
import efub.clone.hanatour.domain.tour.service.TourInfoService;
import efub.clone.hanatour.global.jwt.JwtAuthenticationEntryPoint;
import efub.clone.hanatour.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.common.reflection.XMember;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/tours")
public class TourController {

    private final TourInfoService tourInfoService;
    private final HeartService heartService;

    // Tour 생성
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createTour(@RequestBody TourRequestDto requestDto) {
        tourInfoService.saveTourInfo(requestDto);
    }

    // Tour 상세 조회
    @GetMapping("/{tourId}")
    @ResponseStatus(HttpStatus.OK)
    public TourInfoDetailsDto readTourById(@PathVariable Long tourId) {
        return tourInfoService.findTourById(tourId);
    }

    // Tour 목록 조회 - 검색
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TourListResponseDto readTourList(@RequestParam(name = "keyword", defaultValue = "프랑스") String keyword) {
        return tourInfoService.findTourListByNation(keyword);
    }

    // Tour 목록 조회 - 전체 패키지
    @GetMapping("/package")
    @ResponseStatus(HttpStatus.OK)
    public TotalPackageResponseDto readTourPackages() {
        List<TourInfoWithImageDto> tourListWithImage = tourInfoService.findTourListWithImage();
        return TotalPackageResponseDto.of(tourListWithImage);
    }

    // Tour 좋아요
    @PostMapping("/{tourId}/heart")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createHeart(@RequestHeader("Authorization") String token, @PathVariable Long tourId) {
        log.info(token);
        heartService.createHeart(token, tourId);
        return "좋아요 처리 되었습니다.";
    }


    // Tour 좋아요 삭제
    @DeleteMapping("/{tourId}/heart/{heartId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteHeart(@RequestHeader("Authorization") String token, @PathVariable Long heartId) {
        heartService.deleteHeart(token, heartId);
        return "좋아요를 취소했습니다.";
    }

    // 사용자 별로 좋아요한 Tour 목록 조회
    @GetMapping("/hearts")
    @ResponseStatus(HttpStatus.OK)
    public List<TourInfoDto> getHeartToursByMember() {
        String account = SecurityUtil.getCurrentMemberAccount();
        return heartService.getHeartTours(account);
    }

}
