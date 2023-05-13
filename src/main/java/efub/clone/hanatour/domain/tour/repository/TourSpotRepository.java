package efub.clone.hanatour.domain.tour.repository;

import efub.clone.hanatour.domain.spot.domain.Spot;
import efub.clone.hanatour.domain.tour.domain.Tour;
import efub.clone.hanatour.domain.tour.domain.TourSpot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourSpotRepository extends JpaRepository<TourSpot, Long> {

    TourSpot findByTour(Tour tour);

    List<TourSpot> findAllBySpot(Spot spot);
}
