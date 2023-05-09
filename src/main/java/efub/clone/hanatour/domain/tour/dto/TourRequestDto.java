package efub.clone.hanatour.domain.tour.dto;

import efub.clone.hanatour.domain.tour.domain.Category;
import efub.clone.hanatour.domain.tour.domain.Plan;
import efub.clone.hanatour.domain.tour.domain.Tour;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourRequestDto {

    private String title;
    private String subTitle;
    private String contents;
    private Boolean isLayOver;
    private Boolean isShopping;
    private Category category;
    private Long spotId;
    private LocalDate beginDate;
    private LocalDate endDate;

    public Tour toTourEntity() {
        return Tour.builder()
                .title(this.title)
                .subTitle(this.subTitle)
                .contents(this.contents)
                .category(this.category)
                .isLayOver(this.isLayOver)
                .isShopping(this.isShopping)
                .build();
    }

    public Plan toPlan(Tour tour) {
        return Plan.builder()
                .beginDate(this.beginDate)
                .endDate(this.endDate)
                .tour(tour)
                .build();
    }
}
