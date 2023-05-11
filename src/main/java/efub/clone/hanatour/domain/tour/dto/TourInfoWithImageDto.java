package efub.clone.hanatour.domain.tour.dto;

import efub.clone.hanatour.domain.tour.domain.Category;
import efub.clone.hanatour.domain.tour.domain.Tour;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TourInfoWithImageDto {

    private Long tourId;
    private String title;
    private String subTitle;
    private String contents;
    private String price;
    private Category category;
    private String imageUrl;

    public static TourInfoWithImageDto of(Tour tour, String imageUrl) {
        return TourInfoWithImageDto.builder()
                .tourId(tour.getTourId())
                .title(tour.getTitle())
                .subTitle(tour.getSubTitle())
                .contents(tour.getContents())
                .price(tour.getPrice())
                .category(tour.getCategory())
                .imageUrl(imageUrl)
                .build();
    }
}
