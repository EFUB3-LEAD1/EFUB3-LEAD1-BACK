package efub.clone.hanatour.domain.heart.repository;

import efub.clone.hanatour.domain.heart.domain.Heart;
import efub.clone.hanatour.domain.member.domain.entity.Member;
import efub.clone.hanatour.domain.tour.domain.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    List<Heart> findByMember(Member member);
    boolean existsByMemberAndTour(Member member, Tour tour);
    Optional<Heart> findByMemberAndTour(Member member, Tour tour);
}
