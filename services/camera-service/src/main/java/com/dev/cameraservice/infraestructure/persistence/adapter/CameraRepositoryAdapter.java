package com.dev.cameraservice.infraestructure.persistence.adapter;

import com.dev.cameraservice.domain.model.Camera;
import com.dev.cameraservice.domain.repository.CameraRepository;
import com.dev.cameraservice.infraestructure.persistence.entity.CameraEntity;
import com.dev.cameraservice.infraestructure.persistence.repository.JpaCameraRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CameraRepositoryAdapter implements CameraRepository {

    private final JpaCameraRepository jpaCameraRepository;

    @Override
    public Camera save(Camera camera) {
        CameraEntity entity = toEntity(camera);
        CameraEntity savedEntity = jpaCameraRepository.save(entity);
        return toModel(savedEntity);
    }

    @Override
    public Optional<Camera> findById(Integer id) {
        return jpaCameraRepository.findById(id).map(this::toModel);
    }

    @Override
    public Boolean existsById(Integer id) {
        return jpaCameraRepository.existsById(id);
    }

    @Override
    public List<Camera> findAll() {
        return jpaCameraRepository.findAll().stream()
                .map(this::toModel)
                .toList();
    }

    @Override
    public void deleteById(Integer id) {
        jpaCameraRepository.deleteById(id);
    }

    @Override
    public List<Camera> findByLocation(String location) {
        /*return jpaCameraRepository.findByLocation(location).stream()
                .map(this::toModel)
                .toList();*/
        return List.of();

    }

    private Camera toModel(CameraEntity entity) {
        return new Camera(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getLocation(),
                entity.getUrlStream(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    private CameraEntity toEntity(Camera camera) {
        return new CameraEntity(
                camera.getId(),
                camera.getName(),
                camera.getDescription(),
                camera.getLocation(),
                camera.getUrlStream(),
                camera.getStatus(),
                camera.getCreatedAt(),
                camera.getUpdatedAt()
        );
    }


}
