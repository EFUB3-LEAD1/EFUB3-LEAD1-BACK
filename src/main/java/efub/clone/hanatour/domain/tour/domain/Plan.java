package efub.clone.hanatour.domain.tour.domain;

import efub.clone.hanatour.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "tour_plan")
@Getter
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

    // 기간 계산 (n박 m일에서의 m)
    public int calcNights() {
        return Period.between(beginDate, endDate).getDays();
    }
}
