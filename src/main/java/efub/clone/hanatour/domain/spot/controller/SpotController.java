package efub.clone.hanatour.domain.spot.controller;

import efub.clone.hanatour.domain.spot.dto.SpotListDto;
import efub.clone.hanatour.domain.spot.service.SpotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/spots")
@Tag(name = "여행 Spot에 대한 API", description = "Tour의 관광 지역들을 반환합니다.")
public class SpotController {

    private final SpotService spotService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "여행 지역들을 반환합니다.")
    public List<SpotListDto> readSpotList() {
        return spotService.findSpotList();
    }

}
