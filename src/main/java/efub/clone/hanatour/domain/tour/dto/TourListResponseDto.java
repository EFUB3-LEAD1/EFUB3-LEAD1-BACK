package efub.clone.hanatour.domain.tour.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TourListResponseDto {

    private String keyword;
    private List<TourInfoDto> tours;
    private Integer count;

    public static TourListResponseDto of(String keyword, List<TourInfoDto> tourList) {
        return TourListResponseDto.builder()
                .keyword(keyword)
                .tours(tourList)
                .count(tourList.size())
                .build();
    }

}
