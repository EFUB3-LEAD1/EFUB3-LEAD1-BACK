package efub.clone.hanatour.domain.tour.dto;

import efub.clone.hanatour.domain.spot.domain.Spot;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class SpotDto {
    
    private Long spotId;
    private String continent;
    private String nation;

    public static SpotDto of(Spot spot) {
        return SpotDto.builder()
                .spotId(spot.getSpotId())
                .continent(spot.getContinent())
                .nation(spot.getNation())
                .build();
    }
}
