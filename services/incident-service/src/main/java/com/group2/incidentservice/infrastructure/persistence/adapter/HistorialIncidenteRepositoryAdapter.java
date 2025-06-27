package com.group2.incidentservice.infrastructure.persistence.adapter;

import com.group2.incidentservice.domain.model.HistorialIncidente;
import com.group2.incidentservice.domain.repository.HistorialIncidenteRepository;
import com.group2.incidentservice.infrastructure.persistence.entity.HistorialIncidenteEntity;
import com.group2.incidentservice.infrastructure.persistence.repository.JpaHistorialIncidenteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HistorialIncidenteRepositoryAdapter implements HistorialIncidenteRepository {
    private final JpaHistorialIncidenteRepository jpaHistorialIncidenteRepository;

    @Override
    public HistorialIncidente save(HistorialIncidente historialIncidente) {
        HistorialIncidenteEntity entity = toEntity(historialIncidente);
        HistorialIncidenteEntity savedEntity = jpaHistorialIncidenteRepository.save(entity);
        return toModel(savedEntity);
    }

    @Override
    public void deleteById(Integer id) {
        jpaHistorialIncidenteRepository.deleteById(id);
    }

    private HistorialIncidente toModel(HistorialIncidenteEntity entity) {
        return new HistorialIncidente(
                entity.getId(),
                entity.getIncidenteId(),
                entity.getComentario(),
                entity.getFechaCambio(),
                entity.getContactosNotificados(),
                entity.getEstadoSistema()
        );
    }

    private HistorialIncidenteEntity toEntity(HistorialIncidente historialIncidente) {
        return new HistorialIncidenteEntity(
                historialIncidente.getId(),
                historialIncidente.getIncidenteId(),
                historialIncidente.getComentario(),
                historialIncidente.getFechaCambio(),
                historialIncidente.getContactosNotificados(),
                historialIncidente.getEstadoSistema()
        );
    }
}