package com.dev.cameraservice.infraestructure.config;

import com.dev.cameraservice.domain.repository.CameraRepository;
import com.dev.cameraservice.infraestructure.persistence.adapter.CameraRepositoryAdapter;
import com.dev.cameraservice.infraestructure.persistence.repository.JpaCameraRepository;
import org.springframework.context.annotation.Bean;

public class BeanConfiguration {
    @Bean
    public CameraRepository cameraRepository(JpaCameraRepository jpaCameraRepository) {
        return new CameraRepositoryAdapter(jpaCameraRepository);
    }
}
