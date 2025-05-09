package com.group2.detecthief.api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserProfileDTO(
    UUID id,
    String role,
    String stateSystem,
    boolean active,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
