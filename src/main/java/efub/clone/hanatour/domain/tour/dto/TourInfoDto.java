package efub.clone.hanatour.domain.tour.dto;

import efub.clone.hanatour.domain.tour.domain.Category;
import efub.clone.hanatour.domain.tour.domain.Plan;
import efub.clone.hanatour.domain.tour.domain.Tour;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TourInfoDto {

    private Long tourId;
    private String title;
    private String subTitle;
    private String contents;
    private String price;
    private Category category;
    private PlanDto tourPlan;
    private boolean isHeart;

    public static TourInfoDto of(Tour tour, Plan plan) {
        return TourInfoDto.builder()
                .tourId(tour.getTourId())
                .title(tour.getTitle())
                .subTitle(tour.getSubTitle())
                .contents(tour.getContents())
                .price(tour.getPrice())
                .category(tour.getCategory())
                .tourPlan(PlanDto.of(plan))
                .isHeart(builder().isHeart)
                .build();
    }
}
