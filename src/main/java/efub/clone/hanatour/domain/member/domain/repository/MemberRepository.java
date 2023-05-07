package efub.clone.hanatour.domain.member.domain.repository;

import efub.clone.hanatour.domain.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberInfoByAccount(String account);
    boolean existsByAccount(String account);
}
