package com.group2.detecthief.api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDTO(
        Integer id,
        String nickname,
        String email,
        String nombre,
        String apellido,
        String rol,
        String estadoSistema,
        String estadoNotificaciones,
        LocalDateTime creadoEn,
        LocalDateTime actualizadoEn
) {}
