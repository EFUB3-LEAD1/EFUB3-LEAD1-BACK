package efub.clone.hanatour.domain.tour.domain;

import efub.clone.hanatour.domain.spot.domain.Spot;
import efub.clone.hanatour.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourSpot extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tourSpotId;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @ManyToOne
    @JoinColumn(name = "spot_id")
    private Spot spot;

    @Builder
    public TourSpot(Tour tour, Spot spot) {
        this.tour = tour;
        this.spot = spot;
    }
}
