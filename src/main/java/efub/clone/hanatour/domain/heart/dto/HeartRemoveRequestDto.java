package efub.clone.hanatour.domain.heart.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class HeartRemoveRequestDto {
    @NotNull(message = "잘못된 요청입니다.")
    private Long heartId;
}
