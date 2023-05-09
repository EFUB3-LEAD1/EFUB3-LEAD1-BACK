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
    private Integer days;
    private Integer nights;

    public static PlanDto of(Plan plan) {
        int calcedNights = plan.calcNights();
        return PlanDto.builder()
                .beginDate(plan.getBeginDate())
                .endDate(plan.getEndDate())
                .days(calcedNights - 2)
                .nights(calcedNights)
                .build();
    }
}
