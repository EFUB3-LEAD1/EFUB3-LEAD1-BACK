package efub.clone.hanatour.domain.tour.controller;

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
}
