package com.group2.incidentservice.api.dto;

import java.time.LocalDateTime;

public record IncidentWithTypeDTO(
        Integer id,
        Integer cameraId,
        Integer tipoIncidenteId,
        String tipoIncidenteNombre,
        Integer tipoIncidenteGravedad,
        String estado,
        String descripcion,
        LocalDateTime fechaDetectado,
        String imagenReferencia,
        String estadoSistema,
        LocalDateTime creadoEn,
        LocalDateTime actualizadoEn
) {}