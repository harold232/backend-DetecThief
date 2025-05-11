package com.group2.incidentservice.infrastructure.persistence.adapter;

import com.group2.incidentservice.domain.model.Incident;
import com.group2.incidentservice.domain.repository.IncidentRepository;
import com.group2.incidentservice.infrastructure.persistence.repository.JpaIncidentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class IncidentRepositoryAdapter implements IncidentRepository {

    private final JpaIncidentRepository jpaIncidentRepository;

    @Override
    public Incident save(Incident incident) {
        return null;
    }

    @Override
    public Optional<Incident> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Incident> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Incident> findByCameraId(Long cameraId) {
        return List.of();
    }

    @Override
    public Boolean existsById(Long id) {
        return null;
    }
}
