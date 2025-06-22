package com.dev.cameraservice.infraestructure.persistence.repository;

import com.dev.cameraservice.domain.model.Camera;
import com.dev.cameraservice.infraestructure.persistence.entity.CameraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaCameraRepository extends JpaRepository<CameraEntity, Integer> {
    List<Camera> findByName(String name);
}
