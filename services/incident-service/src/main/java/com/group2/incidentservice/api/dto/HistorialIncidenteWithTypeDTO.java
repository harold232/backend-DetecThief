package com.group2.incidentservice.api.dto;

import java.time.LocalDateTime;

public record HistorialIncidenteWithTypeDTO(
        Integer id,
        String comentario,
        LocalDateTime fechaCambio,
        String contactosNotificados,
        String estadoSistema,
        String evidencia_referencial,
        Integer tipoIncidentId,
        String tipoIncidenteNombre,
        String tipoIncidenteDescripcion,
        Integer tipoIncidenteGravedad
) {}