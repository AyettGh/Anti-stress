package org.example.e_commerce_ai.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/chat")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET");
            }
        };
    }
    // WebConfig.java ou une autre classe de config
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
