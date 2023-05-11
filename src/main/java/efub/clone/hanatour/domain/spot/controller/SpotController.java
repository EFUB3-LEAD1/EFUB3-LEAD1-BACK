package efub.clone.hanatour.domain.spot.controller;

import efub.clone.hanatour.domain.spot.dto.SpotListDto;
import efub.clone.hanatour.domain.spot.service.SpotService;
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
public class SpotController {

    private final SpotService spotService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SpotListDto> readSpotList() {
        return spotService.findSpotList();
    }

}
