package efub.clone.hanatour.domain.tour.repository;

import efub.clone.hanatour.domain.tour.domain.Tour;
import efub.clone.hanatour.domain.tour.domain.TourSpot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourSpotRepository extends JpaRepository<TourSpot, Long> {

    TourSpot findByTour(Tour tour);
}
