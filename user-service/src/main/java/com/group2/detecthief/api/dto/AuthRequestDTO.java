package com.group2.detecthief.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record AuthRequestDTO(
    @Email @NotBlank String email,
    @NotBlank String password
) {}
