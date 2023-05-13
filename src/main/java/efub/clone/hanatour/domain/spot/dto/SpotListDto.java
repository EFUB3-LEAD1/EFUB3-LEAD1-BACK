package efub.clone.hanatour.domain.spot.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SpotListDto {

    private String continent;
    private List<String> nations;

    public static SpotListDto of(String continent, List<String> nations) {
        return SpotListDto.builder()
                .continent(continent)
                .nations(nations)
                .build();
    }
}
