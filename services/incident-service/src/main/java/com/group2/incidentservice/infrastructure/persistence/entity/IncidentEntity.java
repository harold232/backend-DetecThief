package com.group2.incidentservice.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "incidentes")
@NoArgsConstructor
@AllArgsConstructor
public class IncidentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "camara_id", nullable = false)
    private Integer cameraId;

    @Column(name = "tipo_incidente_id", nullable = false)
    private Integer tipoIncidentId;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_detectado", nullable = false)
    private LocalDateTime fechaDetectado;

    @Column(name = "imagen_referencia")
    private String imagenReferencia;

    @Column(name = "estado_sistema", nullable = false)
    private String estadoSistema;

    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en")
    private LocalDateTime actualizadoEn;

    @PrePersist
    protected void onCreate() {
        this.creadoEn = LocalDateTime.now();
        this.actualizadoEn = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.actualizadoEn = LocalDateTime.now();
    }
}