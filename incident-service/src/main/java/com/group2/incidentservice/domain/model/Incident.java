package com.group2.incidentservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Incident {

    private Integer id;
    private Integer cameraId;
    private Integer tipoIncidentId;
    private String estado;
    private String descripcion;
    private LocalDateTime fechaDetectado;
    private String imagenReferencia;
    private String estadoSistema;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

}