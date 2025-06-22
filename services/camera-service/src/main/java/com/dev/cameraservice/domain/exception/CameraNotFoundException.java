package com.dev.cameraservice.domain.exception;

public class CameraNotFoundException extends RuntimeException {
    public CameraNotFoundException(Integer id) {
        super("Cámara con ID " + id + " no encontrada");
    }
}