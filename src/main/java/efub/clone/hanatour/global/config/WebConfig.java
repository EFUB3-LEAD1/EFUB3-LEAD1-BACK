package efub.clone.hanatour.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedOrigins("https://www.htour.xyz/")
                .allowedOrigins("https://tour.xyz/")
                .allowedMethods(HttpMethod.GET.name())
                .allowedMethods(HttpMethod.POST.name())
                .allowedMethods(HttpMethod.PUT.name())
                .allowedMethods(HttpMethod.DELETE.name())
                .allowedHeaders("Authorization")
                .allowedHeaders("refresh-token")
                .allowCredentials(true);
    }
}
