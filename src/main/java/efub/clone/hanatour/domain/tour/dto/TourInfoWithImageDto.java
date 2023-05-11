package efub.clone.hanatour.domain.tour.dto;

import efub.clone.hanatour.domain.tour.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourInfoWithImageDto that = (TourInfoWithImageDto) o;
        return Objects.equals(getTourId(), that.getTourId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourId);
    }
}
