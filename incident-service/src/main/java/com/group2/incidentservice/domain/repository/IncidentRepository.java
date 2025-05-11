package com.group2.incidentservice.domain.repository;

import com.group2.incidentservice.domain.model.Incident;

import java.util.List;
import java.util.Optional;

public interface IncidentRepository {
    Incident save(Incident incident);
    Optional<Incident> findById(Long id);
    List<Incident> findAll();
    void deleteById(Long id);
    List<Incident> findByCameraId(Long cameraId);
    Boolean existsById(Long id);
}
