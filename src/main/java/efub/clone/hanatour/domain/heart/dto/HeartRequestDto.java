package efub.clone.hanatour.domain.heart.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeartRequestDto {
    @NotNull(message = "유저 정보는 반드시 존재해야 합니다.")
    private Long memberId;

    @NotNull(message = "여행 정보는 반드시 존재해야 합니다.")
    private Long tourId;

    @Builder
    public HeartRequestDto(Long memberId, Long tourId) {
        this.memberId = memberId;
        this.tourId = tourId;
    }
}
