package com.group2.incidentservice.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Incident {

    private Integer id;
    private Long cameraId;
    private Long tipoIncidentId;
    private String estado;
    private String descripcion;
    private LocalDateTime fechaDetectado;
    private String imagenReferencia;
    private String estadoSistema;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

}