package com.group2.incidentservice.infrastructure.persistence.adapter;

import com.group2.incidentservice.domain.model.HistorialIncidente;
import com.group2.incidentservice.domain.repository.HistorialIncidenteRepository;
import com.group2.incidentservice.infrastructure.persistence.entity.HistorialIncidenteEntity;
import com.group2.incidentservice.infrastructure.persistence.repository.JpaHistorialIncidenteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<HistorialIncidente> findAll() {
        return jpaHistorialIncidenteRepository.findAll().stream()
                .map(this::toModel)
                .toList();
    }

    @Override
    public Optional<HistorialIncidente> findById(Integer id) {
        return jpaHistorialIncidenteRepository.findById(id).map(this::toModel);
    }
    @Override
    public void updateEstadoSistema(Integer id, String estadoSistema) {
        jpaHistorialIncidenteRepository.findById(id).ifPresent(entity -> {
            entity.setEstadoSistema(estadoSistema);
            jpaHistorialIncidenteRepository.save(entity);
        });
    }

    private HistorialIncidente toModel(HistorialIncidenteEntity entity) {
        return new HistorialIncidente(
                entity.getId(),
                entity.getComentario(),
                entity.getFechaCambio(),
                entity.getContactosNotificados(),
                entity.getEstadoSistema()
        );
    }

    private HistorialIncidenteEntity toEntity(HistorialIncidente historialIncidente) {
        return new HistorialIncidenteEntity(
                historialIncidente.getId(),
                historialIncidente.getComentario(),
                historialIncidente.getFechaCambio(),
                historialIncidente.getContactosNotificados(),
                historialIncidente.getEstadoSistema()
        );
    }
}