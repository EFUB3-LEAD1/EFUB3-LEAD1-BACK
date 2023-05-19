package efub.clone.hanatour;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@OpenAPIDefinition(servers = {@Server(url = "https://api.htour.xyz", description = "배포된 서버")})
@SpringBootApplication
public class HanatourApplication {

	public static void main(String[] args) {
		SpringApplication.run(HanatourApplication.class, args);
	}

}
