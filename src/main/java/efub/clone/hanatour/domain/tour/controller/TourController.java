package efub.clone.hanatour.domain.tour.controller;

import efub.clone.hanatour.domain.heart.service.HeartService;
import efub.clone.hanatour.domain.tour.dto.*;
import efub.clone.hanatour.domain.tour.service.TourInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tour 관광상품 API", description = "관광상품에 대한 정보를 반환하는 API입니다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/tours")
public class TourController {

    private final TourInfoService tourInfoService;
    private final HeartService heartService;

    // Tour 생성
    @Operation(summary = "테스트를 위해 개발한, 사용하지 않는 API입니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createTour(@RequestBody TourRequestDto requestDto) {
        tourInfoService.saveTourInfo(requestDto);
    }

    // Tour 상세 조회
    @Operation(summary = "Tour 관광상품 정보를 개별로 상세 조회하는 API입니다.")
    @Parameter(name = "tourId", description = "tourId를 넣으셔야 합니다.")
    @GetMapping("/{tourId}")
    @ResponseStatus(HttpStatus.OK)
    public TourInfoDetailsDto readTourById(@PathVariable Long tourId) {
        return tourInfoService.findTourById(tourId);
    }

    // Tour 목록 조회 - 검색
    @Operation(summary = "Tour 관광상품 정보를 나라 이름으로 검색하는 API입니다.")
    @GetMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    public TourListResponseDto readTourList(@RequestParam(name = "keyword", defaultValue = "프랑스") String keyword) {
        return tourInfoService.findTourListByNation(keyword);
    }

    // Tour 목록 조회 - 전체 패키지
    @Operation(summary = "Tour 관광상품 정보를 전체 반환하는 API입니다.")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public TotalPackageResponseDto readTourPackages() {
        List<TourInfoWithImageDto> tourListWithImage = tourInfoService.findTourListWithImage();
        return TotalPackageResponseDto.of(tourListWithImage);
    }

}
