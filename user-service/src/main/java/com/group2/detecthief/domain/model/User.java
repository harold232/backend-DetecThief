package com.group2.detecthief.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {
    private UUID id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserProfile profile;

    // Constructor
    public User(UUID id, String username, String email, String firstName, String lastName,
                boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Constructor con perfil
    public User(UUID id, String username, String email, String firstName, String lastName,
                boolean active, LocalDateTime createdAt, LocalDateTime updatedAt, UserProfile profile) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.profile = profile;
    }

    // Constructor sin ID para creaciones nuevas
    public User(String username, String email, String firstName, String lastName) {
        this.id = null;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = true;
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
        this.active = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.profile = new UserProfile(null, role, true);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    // Métodos de dominio
    public void activate() {
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
    }

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
