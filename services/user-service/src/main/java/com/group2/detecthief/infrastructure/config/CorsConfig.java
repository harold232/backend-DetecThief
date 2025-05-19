package com.group2.detecthief.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    // Configuración WebMvcConfigurer
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173") // Tu frontend React
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }

    // Filtro CORS adicional para garantizar que todas las solicitudes sean procesadas correctamente
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Permitir solicitudes desde este origen
        config.addAllowedOrigin("http://localhost:5173");

        // Permitir credenciales (cookies, encabezados de autorización, etc.)
        config.setAllowCredentials(true);

        // Permitir estos encabezados en las solicitudes
        config.addAllowedHeader("*");

        // Permitir los métodos HTTP necesarios
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        config.addAllowedMethod("OPTIONS");

        // Exponer encabezados que el frontend puede acceder
        config.addExposedHeader("Authorization");

        // Aplicar esta configuración a todas las rutas
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}