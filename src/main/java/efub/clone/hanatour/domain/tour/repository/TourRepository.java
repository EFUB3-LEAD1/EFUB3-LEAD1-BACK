package efub.clone.hanatour.domain.tour.repository;

import efub.clone.hanatour.domain.tour.domain.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour, Long> {
    
}
