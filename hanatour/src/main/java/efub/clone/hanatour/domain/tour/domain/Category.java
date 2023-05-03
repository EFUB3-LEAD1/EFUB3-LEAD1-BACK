package efub.clone.hanatour.domain.tour.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    BEST(0, "인기베스트상품"),
    SOLDOUT(1, "조기완판"),
    HOTDEAL(2, "금주핫딜");

    private final Integer id;
    private final String title;
}
