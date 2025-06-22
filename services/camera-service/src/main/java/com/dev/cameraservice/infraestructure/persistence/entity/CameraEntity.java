package com.dev.cameraservice.infraestructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "camaras")
@NoArgsConstructor
@AllArgsConstructor
public class CameraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String name;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "ubicacion", nullable = false)
    private String location;

    @Column(name = "url_stream", nullable = false)
    private String urlStream;

    @Column(name = "estado_sistema", nullable = false)
    private String status;

    @Column(name = "creada_en", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "actualizada_en")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
