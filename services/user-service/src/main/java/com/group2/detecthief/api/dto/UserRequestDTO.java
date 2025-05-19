package com.group2.detecthief.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record UserRequestDTO(
        @NotBlank(message = "El nickname es obligatorio")
        @Size(min = 3, max = 50, message = "El nickname debe tener entre 3 y 50 caracteres")
        String nickname,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Formato de email inválido")
        String email,

        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        String apellido
) {
}