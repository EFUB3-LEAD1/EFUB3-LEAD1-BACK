package efub.clone.hanatour.domain.tour.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TotalPackageResponseDto {

    private List<TourInfoWithImageDto> tours;
    private Integer count;

    public static TotalPackageResponseDto of(List<TourInfoWithImageDto> tourList) {
        return TotalPackageResponseDto.builder()
                .tours(tourList)
                .count(tourList.size())
                .build();
    }
}
