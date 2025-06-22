package com.dev.cameraservice.api.controller;

import com.dev.cameraservice.api.dto.CameraRequestDTO;
import com.dev.cameraservice.api.dto.CameraResponseDTO;
import com.dev.cameraservice.application.mapper.CameraMapper;
import com.dev.cameraservice.application.service.CameraService;
import com.dev.cameraservice.domain.model.Camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cameras")
public class CameraController {

    @Autowired
    private CameraService cameraService;
    @Autowired
    private CameraMapper cameraMapper;

    @PostMapping
    public ResponseEntity<Camera> createCamera(@RequestBody CameraRequestDTO camera) {
        Camera createdCamera = cameraService.createCamera(camera);
        return ResponseEntity.ok(createdCamera);
    }

    @GetMapping
    public ResponseEntity<List<CameraResponseDTO>> getAllCameras() {
        List<Camera> cameras = cameraService.getAllCameras();
        List<CameraResponseDTO> response = cameras.stream()
                .map(cameraMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CameraResponseDTO> getCameraById(@PathVariable Integer id) {
        Camera camera = cameraService.getCameraById(id);
        if (camera == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cameraMapper.toResponseDTO(camera));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CameraResponseDTO> updateCamera(@PathVariable Integer id, @RequestBody Camera camera) {
        Camera updateCamera = cameraService.updateCamera(id, camera);
        return ResponseEntity.ok(cameraMapper.toResponseDTO(updateCamera));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCamera(@PathVariable Integer id) {
        cameraService.deleteCamera(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<CameraResponseDTO> activateCamera(@PathVariable Integer id) {
        Camera activatedCamera = cameraService.activateCamera(id);
        return ResponseEntity.ok(cameraMapper.toResponseDTO(activatedCamera));
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<CameraResponseDTO> deactivateCamera(@PathVariable Integer id) {
        Camera deactivatedCamera = cameraService.deactivateCamera(id);
        return ResponseEntity.ok(cameraMapper.toResponseDTO(deactivatedCamera));
    }
}
