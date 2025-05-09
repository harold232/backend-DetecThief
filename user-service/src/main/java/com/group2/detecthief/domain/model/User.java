package com.group2.detecthief.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {
    private UUID id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private UserProfile profile;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructor
    public User(UUID id, String username, String email, String firstName, String lastName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Constructor sin ID para creaciones nuevas
    public User(String username, String email, String firstName, String lastName) {
        this.id = null;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Constructor sin ID para creaciones nuevas con rol
    public User(String username, String email, String firstName, String lastName, String role) {
        this.id = null;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profile = new UserProfile(null, role, true);
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        
    }

    // Métodos de dominio
    /*public void activate() {
        this.active = true;
        this.updatedAt = LocalDateTime.now();
        if (this.profile != null) {
            this.profile.setStateSystem("activo");
            this.profile.setUpdatedAt(LocalDateTime.now());
        }
    }

    public void deactivate() {
        this.active = false;
        this.updatedAt = LocalDateTime.now();
        if (this.profile != null) {
            this.profile.setStateSystem("inactivo");
            this.profile.setUpdatedAt(LocalDateTime.now());
        }
    }*/

    public void update(String username, String email, String firstName, String lastName) {
        if (username != null) this.username = username;
        if (email != null) this.email = email;
        if (firstName != null) this.firstName = firstName;
        if (lastName != null) this.lastName = lastName;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateRole(String role) {
        if (this.profile != null) {
            this.profile.setRole(role);
            this.profile.setUpdatedAt(LocalDateTime.now());
        } else {
            this.profile = new UserProfile(null, role, true);
        }
    }
}
