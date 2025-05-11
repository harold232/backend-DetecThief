package com.group2.incidentservice.infrastructure.persistence.adapter;

import com.group2.incidentservice.domain.model.Incident;
import com.group2.incidentservice.domain.repository.IncidentRepository;
import com.group2.incidentservice.infrastructure.persistence.entity.IncidentEntity;
import com.group2.incidentservice.infrastructure.persistence.repository.JpaIncidentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class IncidentRepositoryAdapter implements IncidentRepository {

    private final JpaIncidentRepository jpaIncidentRepository;

    @Override
    public Incident save(Incident incident) {
        IncidentEntity entity = toEntity(incident);
        IncidentEntity savedEntity = jpaIncidentRepository.save(entity);
        return toModel(savedEntity);
    }

    @Override
    public Optional<Incident> findById(Integer id) {
        return jpaIncidentRepository.findById(id).map(this::toModel);
    }

    @Override
    public List<Incident> findAll() {
        return jpaIncidentRepository.findAll().stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        jpaIncidentRepository.deleteById(id);
    }

    @Override
    public List<Incident> findByCameraId(Integer cameraId) {
        return jpaIncidentRepository.findByCameraId(cameraId).stream()
                .map(this::toModel)
                .toList();
    }

    @Override
    public Boolean existsById(Integer id) {
        return jpaIncidentRepository.existsById(id);
    }

    private Incident toModel(IncidentEntity entity) {
        return new Incident(
                entity.getId(),
                entity.getCameraId(),
                entity.getTipoIncidentId(),
                entity.getEstado(),
                entity.getDescripcion(),
                entity.getFechaDetectado(),
                entity.getImagenReferencia(),
                entity.getEstadoSistema(),
                entity.getCreadoEn(),
                entity.getActualizadoEn());
    }

    private IncidentEntity toEntity(Incident incident) {
        return new IncidentEntity(
                incident.getId(),
                incident.getCameraId(),
                incident.getTipoIncidentId(),
                incident.getEstado(),
                incident.getDescripcion(),
                incident.getFechaDetectado(),
                incident.getImagenReferencia(),
                incident.getEstadoSistema(),
                incident.getCreadoEn(),
                incident.getActualizadoEn());
    }
}
