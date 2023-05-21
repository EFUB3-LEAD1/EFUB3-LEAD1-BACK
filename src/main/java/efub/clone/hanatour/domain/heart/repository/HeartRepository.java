package efub.clone.hanatour.domain.heart.repository;

import efub.clone.hanatour.domain.heart.domain.Heart;
import efub.clone.hanatour.domain.member.entity.Member;
import efub.clone.hanatour.domain.tour.domain.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {
    boolean existsByMemberAccountIdAndTour(Member memberAccountId, Tour tour);
    List<Heart> findByMemberAccountIdAndTour(Member member, Tour tour);
    Optional<Heart> findFirstByMemberAccountIdAndTour(Member memberAccountId, Tour tour);
//    Optional<Heart> findFirstByMemberAccountId(Long memberAccountId);
//    List<Heart> findByMemberAccountId(Long memberId);
    List<Heart> findByMemberAccountId(Member member);

    Optional<Heart> findByHeartIdAndMemberAccountId(Long heartId, Member member);

    Heart findByTourAndMemberAccountId(Tour tour, Member member);

}
