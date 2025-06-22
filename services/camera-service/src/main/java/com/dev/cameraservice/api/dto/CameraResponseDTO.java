package com.dev.cameraservice.api.dto;

public record CameraResponseDTO(
        Integer id,
        String name,
        String description,
        String location,
        String urlStream,
        String status,
        String createdAt,
        String updatedAt
) {
}
