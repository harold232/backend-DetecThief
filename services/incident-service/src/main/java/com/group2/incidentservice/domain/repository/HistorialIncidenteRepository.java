package com.group2.incidentservice.domain.repository;

import com.group2.incidentservice.domain.model.HistorialIncidente;

public interface HistorialIncidenteRepository {
    HistorialIncidente save(HistorialIncidente historialIncidente);
    void deleteById(Integer id);
}