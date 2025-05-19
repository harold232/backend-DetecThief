package com.group2.incidentservice.domain.repository;

import com.group2.incidentservice.domain.model.Incident;

import java.util.List;
import java.util.Optional;

public interface IncidentRepository {
    Incident save(Incident incident);
    Optional<Incident> findById(Integer id);
    List<Incident> findAll();
    void deleteById(Integer id);
    List<Incident> findByCameraId(Integer cameraId);
    Boolean existsById(Integer id);
}
