package efub.clone.hanatour.domain.tour.controller;

//import efub.clone.hanatour.domain.heart.dto.HeartRequestDto;
//import efub.clone.hanatour.domain.heart.service.HeartService;
import efub.clone.hanatour.domain.tour.dto.*;
import efub.clone.hanatour.domain.tour.service.TourInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tours")
public class TourController {

    private final TourInfoService tourInfoService;
//    private final HeartService heartService;

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
    /*@PostMapping("/{tourId}/heart")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createTourHeart(@PathVariable final Long tourId, @RequestBody final HeartRequestDto requestDto) {
        heartService.createHeart(tourId, requestDto);
        return "좋아요를 눌렀습니다.";
    }*/

    // Tour 좋아요 삭제
    /*@DeleteMapping("/{tourId}/heart")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteTourHeart(@PathVariable final Long tourId, @RequestParam final Long accountId) {
        heartService.deleteHeart(tourId, accountId);
        return "좋아요가 취소되었습니다.";
    }*/
}
