package com.group2.incidentservice.api.dto;

import java.time.LocalDateTime;

public record HistorialIncidenteDTO(
        Integer id,
        String comentario,
        LocalDateTime fechaCambio,
        String contactosNotificados,
        String estadoSistema
) {}