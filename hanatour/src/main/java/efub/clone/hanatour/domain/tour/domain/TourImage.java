package efub.clone.hanatour.domain.tour.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Image를 밸류로 구현하는 경우
 */
@Embeddable
public class TourImage {

    @Column(name = "url", nullable = false)
    private String url;
}
