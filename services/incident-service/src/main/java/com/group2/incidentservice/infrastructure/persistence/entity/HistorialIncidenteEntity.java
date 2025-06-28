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
@Table(name = "historial_incidentes")
@NoArgsConstructor
@AllArgsConstructor
public class HistorialIncidenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "fecha_cambio", nullable = false)
    private LocalDateTime fechaCambio;

    @Column(name = "contactos_notificados")
    private String contactosNotificados;

    @Column(name = "estado_sistema", nullable = false)
    private String estadoSistema;

    @PrePersist
    protected void onCreate() {
        this.fechaCambio = LocalDateTime.now();
        if (this.estadoSistema == null) {
            this.estadoSistema = "activo";
        }
    }
}
