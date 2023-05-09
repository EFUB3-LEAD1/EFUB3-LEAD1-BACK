package efub.clone.hanatour.domain.tour.controller;

import efub.clone.hanatour.domain.tour.dto.TourInfoDetailsDto;
import efub.clone.hanatour.domain.tour.dto.TourRequestDto;
import efub.clone.hanatour.domain.tour.service.TourInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
