package efub.clone.hanatour.domain.tour.domain;

import efub.clone.hanatour.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tour_plan")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Plan extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @Column(nullable = false)
    private LocalDate beginDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @OneToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @Builder
    public Plan(LocalDate beginDate, LocalDate endDate, Tour tour) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.tour = tour;
    }
}
