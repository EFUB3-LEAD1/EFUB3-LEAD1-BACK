package efub.clone.hanatour.domain.spot.repository;

import efub.clone.hanatour.domain.spot.domain.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpotRepository extends JpaRepository<Spot, Long> {

    Spot findByNation(String nation);

    @Query(value = "select spot " +
            "from Spot spot join TourSpot ts " +
            "on ts.spot = spot " +
            "where ts.tour.tourId = :tourId")
    List<Spot> findAllByTour(@Param("tourId") Long tourId);
}
