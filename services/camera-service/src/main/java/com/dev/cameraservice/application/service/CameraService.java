package com.dev.cameraservice.application.service;

import com.dev.cameraservice.api.dto.CameraRequestDTO;
import com.dev.cameraservice.application.mapper.CameraMapper;
import com.dev.cameraservice.domain.model.Camera;
import com.dev.cameraservice.domain.repository.CameraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CameraService {
    private final CameraRepository cameraRepository;
    private final CameraMapper cameraMapper;

    public CameraService(CameraRepository cameraRepository, CameraMapper cameraMapper) {
        this.cameraRepository = cameraRepository;
        this.cameraMapper = cameraMapper;
    }

    public Camera createCamera(CameraRequestDTO cameraRequestDTO) {
        Camera camera = cameraMapper.toModel(cameraRequestDTO);
        return cameraRepository.save(camera);
    }

    public List<Camera> getAllCameras() {
        return cameraRepository.findAll();
    }

    public Camera getCameraById(Integer id) {
        return cameraRepository.findById(id).orElse(null);
    }

    public Camera updateCamera(Integer id, Camera camera) {
        if (cameraRepository.existsById(id)) {
            camera.setId(id);
            return cameraRepository.save(camera);
        }
        return null;
    }

    public void deleteCamera(Integer id) {
        cameraRepository.deleteById(id);
    }

    public Camera activateCamera(Integer id) {
        return updateCameraStatus(id, "Activo");
    }

    public Camera deactivateCamera(Integer id) {
        return updateCameraStatus(id, "Inactivo");
    }

    public Camera updateCameraStatus(Integer id, String nuevoEstado) {
        return cameraRepository.findById(id).map(camera -> {
            camera.setStatus(nuevoEstado);
            return cameraRepository.save(camera);
        }).orElse(null);
    }

}
