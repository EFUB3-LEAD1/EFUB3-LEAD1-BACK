package efub.clone.hanatour.domain.spot.service;

import efub.clone.hanatour.domain.spot.domain.Spot;
import efub.clone.hanatour.domain.spot.dto.SpotListDto;
import efub.clone.hanatour.domain.spot.repository.SpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SpotService {

    private final SpotRepository spotRepository;

    public List<SpotListDto> findSpotList() {
        List<Spot> spotList = spotRepository.findAll();
        Map<String, List<String>> map = new HashMap<>();    // <대륙, [국가1, 국가2, ...]> 매핑
        for (Spot spot : spotList) {
            String continent = spot.getContinent();
            String nation = spot.getNation();
            if (map.containsKey(continent)) {
                map.get(continent).add(nation);
            } else {
                List<String> nationList = new ArrayList<>();
                nationList.add(nation);
                map.put(continent, nationList);
            }
        }
        List<SpotListDto> dtoList = new ArrayList<>();
        map.forEach((c, n) -> dtoList.add(SpotListDto.of(c, n)));
        return dtoList;
    }
}
