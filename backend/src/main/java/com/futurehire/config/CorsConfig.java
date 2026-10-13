package com.futurehire.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {

    @Value("${futurehire.cors.allowed-origins:https://futurehire-frontend.vercel.app,http://localhost:3000,http://localhost:5173}")
    private String allowedOrigins;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                List<String> origins = new ArrayList<>(Arrays.asList(
                    "https://*.vercel.app",
                    "https://futurehire-frontend.vercel.app",
                    "http://localhost:3000",
                    "http://localhost:5173"
                ));
                if (allowedOrigins != null && !allowedOrigins.isBlank()) {
                    origins.addAll(Arrays.stream(allowedOrigins.split(","))
                        .map(String::trim)
                        .filter(o -> !o.isEmpty())
                        .toList());
                }

                registry.addMapping("/**")
                    .allowedOriginPatterns(origins.toArray(String[]::new))
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true)
                    .maxAge(3600);
            }
        };
    }
}
