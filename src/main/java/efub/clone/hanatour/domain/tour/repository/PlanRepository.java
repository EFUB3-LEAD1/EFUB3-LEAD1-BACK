package efub.clone.hanatour.domain.tour.repository;

import efub.clone.hanatour.domain.tour.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
