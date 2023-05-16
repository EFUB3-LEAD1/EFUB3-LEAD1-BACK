package efub.clone.hanatour.domain.heart.domain;

import efub.clone.hanatour.domain.member.domain.entity.Member;
import efub.clone.hanatour.domain.tour.domain.Tour;
import efub.clone.hanatour.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long heartId;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @Builder
    public Heart(Tour tour, Member member) {
        this.tour = tour;
        this.member = member;
    }
}