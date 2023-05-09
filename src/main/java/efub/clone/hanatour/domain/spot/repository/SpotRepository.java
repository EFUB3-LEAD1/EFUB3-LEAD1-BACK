package efub.clone.hanatour.domain.spot.repository;

import efub.clone.hanatour.domain.spot.domain.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<Spot, Long> {
}
