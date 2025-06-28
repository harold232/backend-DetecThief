package com.group2.incidentservice.api.dto;

public record UserDTO(
        Integer id,
        String nickname,
        String email,
        String nombre,
        String apellido,
        String rol,
        String estadoSistema,
        String estadoNotificaciones
) {}
