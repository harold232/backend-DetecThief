package com.group2.detecthief.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "usuarios_perfil")
public class UserProfileEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private String role;

    @Column(name = "creado_en", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "actualizado_en", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "estado_sistema", nullable = false)
    private String stateSystem;

    // Constructor vacío necesario para JPA
    public UserProfileEntity() {
    }

    // Constructor con todos los campos
    public UserProfileEntity(UUID id, UserEntity user, String role, LocalDateTime createdAt,
                             LocalDateTime updatedAt, String stateSystem) {
        this.id = id;
        this.user = user;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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

    // Métodos de ciclo de vida
    @PrePersist
    protected void onCreate() {
        if (id == null) {
            id = UUID.randomUUID();
        }
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (updatedAt == null) {
            updatedAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

