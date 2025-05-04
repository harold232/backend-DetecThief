package com.group2.detecthief.api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserProfileDTO {
    private UUID id;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String stateSystem;

    public UserProfileDTO(UUID id, String role, LocalDateTime createdAt, LocalDateTime updatedAt, String stateSystem) {
        this.id = id;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.stateSystem = stateSystem;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStateSystem() {
        return stateSystem;
    }

    public void setStateSystem(String stateSystem) {
        this.stateSystem = stateSystem;
    }

}
