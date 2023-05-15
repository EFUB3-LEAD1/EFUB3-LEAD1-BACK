package efub.clone.hanatour.domain.tour.dto;

import efub.clone.hanatour.domain.tour.domain.Plan;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class PlanDto {

    private LocalDate beginDate;
    private LocalDate endDate;
    private String duration;

    public static PlanDto of(Plan plan) {
        int duration = plan.calcDuration();
        return PlanDto.builder()
                .beginDate(plan.getBeginDate())
                .endDate(plan.getEndDate())
                .duration(String.format("%d박 %d일", duration - 2, duration))
                .build();
    }
}
