package efub.clone.hanatour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HanatourApplication {

	public static void main(String[] args) {
		SpringApplication.run(HanatourApplication.class, args);
	}

}
