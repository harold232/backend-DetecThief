package com.dev.cameraservice.api.dto;

public record CameraRequestDTO (
        String name,
        String description,
        String location,
        String urlStream
) {
}
