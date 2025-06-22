package com.dev.cameraservice.domain.repository;

import com.dev.cameraservice.domain.model.Camera;
import com.dev.cameraservice.infraestructure.persistence.entity.CameraEntity;

import java.util.List;
import java.util.Optional;

public interface CameraRepository {
    Camera save(Camera camera);
    Optional<Camera> findById(Integer id);
    List<Camera> findAll();
    void deleteById(Integer id);
    List<Camera> findByLocation(String location);
    Boolean existsById(Integer id);
}
