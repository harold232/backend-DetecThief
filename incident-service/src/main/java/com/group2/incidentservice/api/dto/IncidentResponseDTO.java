package com.group2.incidentservice.api.dto;

import java.time.LocalDateTime;

public record IncidentResponseDTO(
        Integer id,
        Integer cameraId,
        Integer tipoIncidentId,
        String estado,
        String descripcion,
        LocalDateTime fechaDetectado,
        String imagenReferencia,
        String estadoSistema,
        LocalDateTime creadoEn,
        LocalDateTime actualizadoEn
) {
}