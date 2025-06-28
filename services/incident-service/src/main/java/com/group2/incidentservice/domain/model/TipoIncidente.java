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
public class TipoIncidente {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer gravedad;
    private String estadoSistema;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;
}