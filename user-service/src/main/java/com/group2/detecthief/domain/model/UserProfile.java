package com.group2.detecthief.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserProfile {

    private UUID id;
    private String role;
    private String stateSystem;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User user;

    public UserProfile(UUID id, String role, boolean active) {
        this.id = id;
        this.role = role != null ? role : "USER";
        this.stateSystem = active ? "ACTIVE" : "INACTIVE";
        this.active = active;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Métodos de dominio
    public void activate() {
        this.active = true;
        this.updatedAt = LocalDateTime.now();
        if (this.user != null) {
            this.user.setUpdatedAt(LocalDateTime.now());
        }
    }

    public void deactivate() {
        this.active = false;
        this.updatedAt = LocalDateTime.now();
        if (this.user != null) {
            this.user.setUpdatedAt(LocalDateTime.now());
        }
    }

}
