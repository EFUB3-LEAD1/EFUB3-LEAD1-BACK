package efub.clone.hanatour.domain.tour.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    BEST("TOUR_BEST", "인기베스트상품"),
    SOLD_OUT("TOUR_SOLD_OUT", "조기완판"),
    HOT_DEAL("TOUR_HOT_DEAL", "금주핫딜");

    private final String name;
    private final String title;
}
