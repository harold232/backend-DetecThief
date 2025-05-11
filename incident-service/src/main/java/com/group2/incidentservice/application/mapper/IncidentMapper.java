package com.group2.incidentservice.application.mapper;

import com.group2.incidentservice.api.dto.IncidentRequestDTO;
import com.group2.incidentservice.api.dto.IncidentResponseDTO;
import com.group2.incidentservice.domain.model.Incident;
import org.springframework.stereotype.Component;

@Component
public class IncidentMapper {

    public Incident toModel(IncidentRequestDTO dto) {
        Incident incident = new Incident();
        incident.setCameraId(dto.cameraId());
        incident.setTipoIncidentId(dto.tipoIncidentId());
        incident.setEstado(dto.estado());
        incident.setDescripcion(dto.descripcion());
        incident.setFechaDetectado(dto.fechaDetectado());
        incident.setImagenReferencia(dto.imagenReferencia());
        incident.setEstadoSistema(dto.estadoSistema());
        return incident;
    }

    public IncidentResponseDTO toResponseDTO(Incident incident) {
        return new IncidentResponseDTO(
                incident.getId(),
                incident.getCameraId(),
                incident.getTipoIncidentId(),
                incident.getEstado(),
                incident.getDescripcion(),
                incident.getFechaDetectado(),
                incident.getImagenReferencia(),
                incident.getEstadoSistema(),
                incident.getCreadoEn(),
                incident.getActualizadoEn()
        );
    }
}