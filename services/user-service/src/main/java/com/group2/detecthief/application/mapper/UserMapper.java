package com.group2.detecthief.application.mapper;

import com.group2.detecthief.api.dto.UserRequestDTO;
import com.group2.detecthief.api.dto.UserResponseDTO;
import com.group2.detecthief.domain.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    public User toModel(UserRequestDTO dto) {
        return new User(
                null,
                dto.nickname(),
                dto.email(),
                dto.nombre(),
                dto.apellido(),
                "operador", // Rol por defecto
                "activo",
                "inactivo",// Estado por defecto
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getNickname(),
                user.getEmail(),
                user.getNombre(),
                user.getApellido(),
                user.getRol(),
                user.getEstadoSistema(),
                user.getEstadoNotificaciones(),
                user.getCreadoEn(),
                user.getActualizadoEn()
        );
    }
}