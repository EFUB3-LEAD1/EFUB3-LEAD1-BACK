package efub.clone.hanatour.domain.member.dto;

import efub.clone.hanatour.domain.member.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema
public class MemberRequestDto {

    @Schema(description = "사용자 계정", defaultValue = "abc@gmail.com")
    private String account;

    @Schema(description = "사용자 비밀번호", defaultValue = "dkvnxlejrowqidjf")
    private String password;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .account(account)
                .password(passwordEncoder.encode(password))
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(account, password);
    }
}
