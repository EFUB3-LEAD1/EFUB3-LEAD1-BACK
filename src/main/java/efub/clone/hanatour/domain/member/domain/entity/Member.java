package efub.clone.hanatour.domain.member.domain.entity;

import efub.clone.hanatour.domain.tour.domain.Tour;
import efub.clone.hanatour.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(unique = true, nullable = false, updatable = false, length = 64)
    private String account;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 32)
    private String nickname;

    @Builder
    public Member(String account, String password, String nickname) {
        this.account = account;
        this.password = password;
        this.nickname = nickname;
    }
}
