package com.mop.what2c;
// CORS 허용 설정
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/") // cors를 적용할 spring서버의 url 패턴.
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT");
    }
}
