package com.group2.detecthief.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuarios_perfil")
public class UserProfileEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserEntity user;

    @Column(name="rol", nullable = false)
    private String role;

    @Column(name = "estado_sistema", nullable = false)
    private String stateSystem;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Column(name = "creado_en", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "actualizado_en", nullable = false)
    private LocalDateTime updatedAt;


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

