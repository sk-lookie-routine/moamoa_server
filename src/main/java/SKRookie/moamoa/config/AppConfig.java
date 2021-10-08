package SKRookie.moamoa.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    // modelMapper 등록
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}