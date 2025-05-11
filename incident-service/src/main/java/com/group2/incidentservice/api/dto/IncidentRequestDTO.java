package com.group2.incidentservice.api.dto;

import java.time.LocalDateTime;

public record IncidentRequestDTO(
        Long cameraId,
        Long tipoIncidentId,
        String estado,
        String descripcion,
        LocalDateTime fechaDetectado,
        String imagenReferencia,
        String estadoSistema
) {
}