package efub.clone.hanatour.domain.heart.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeartRemoveRequestDto {
    @NotNull(message = "잘못된 요청입니다.")
    private Long tourId;
}
