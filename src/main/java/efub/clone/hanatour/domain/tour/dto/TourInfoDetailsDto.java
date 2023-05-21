package efub.clone.hanatour.domain.tour.dto;

import efub.clone.hanatour.domain.image.domain.Image;
import efub.clone.hanatour.domain.spot.domain.Spot;
import efub.clone.hanatour.domain.spot.dto.SpotDto;
import efub.clone.hanatour.domain.tour.domain.Category;
import efub.clone.hanatour.domain.tour.domain.Plan;
import efub.clone.hanatour.domain.tour.domain.Tour;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TourInfoDetailsDto {

    private Long tourId;
    private String title;
    private String subTitle;
    private String contents;
    private String price;
    private Boolean isHeart;
    private Boolean isLayOver;
    private Boolean isShopping;
    private Category category;
    private List<SpotDto> tourSpot;
    private PlanDto tourPlan;
    private List<ImageDto> images;

    public static TourInfoDetailsDto of(Tour tour, List<Spot> spotList, Plan plan, List<Image> imageList, Boolean isHeart) {
        return TourInfoDetailsDto.builder()
                .tourId(tour.getTourId())
                .title(tour.getTitle())
                .subTitle(tour.getSubTitle())
                .contents(tour.getContents())
                .price(tour.getPrice())
                .isHeart(isHeart)
                .isLayOver(tour.getIsLayOver())
                .isShopping(tour.getIsShopping())
                .category(tour.getCategory())
                .tourSpot(spotList.stream().map(SpotDto::of).collect(Collectors.toList()))
                .tourPlan(PlanDto.of(plan))
                .images(ImageDto.toList(imageList))
                .build();
    }
}
