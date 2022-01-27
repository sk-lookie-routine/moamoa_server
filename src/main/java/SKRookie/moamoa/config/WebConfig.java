package SKRookie.moamoa.config;

import SKRookie.moamoa.config.properties.CorsProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    CorsProperties corsProperties = new CorsProperties();
    @Override
    public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins(corsProperties.getAllowedOrigins())
                    .allowedMethods(
                            HttpMethod.GET.name(),
                            HttpMethod.HEAD.name(),
                            HttpMethod.POST.name(),
                            HttpMethod.PUT.name(),
                            HttpMethod.DELETE.name())
                    .allowedHeaders(corsProperties.getAllowedHeaders());
    }
}
