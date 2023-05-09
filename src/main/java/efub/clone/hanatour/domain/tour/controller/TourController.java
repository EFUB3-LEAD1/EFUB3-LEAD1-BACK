package efub.clone.hanatour.domain.tour.controller;

import efub.clone.hanatour.domain.tour.dto.TourRequestDto;
import efub.clone.hanatour.domain.tour.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tours")
public class TourController {

    private final TourService tourService;

    // Tour 생성
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createTour(@RequestBody TourRequestDto requestDto) {
        tourService.saveTourInfo(requestDto);
    }
}
