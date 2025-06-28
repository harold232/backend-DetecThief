package com.group2.incidentservice.domain.repository;

import com.group2.incidentservice.domain.model.HistorialIncidente;

import java.util.List;
import java.util.Optional;

public interface HistorialIncidenteRepository {
    HistorialIncidente save(HistorialIncidente historialIncidente);
    void deleteById(Integer id);
    List<HistorialIncidente> findAll();
    Optional<HistorialIncidente> findById(Integer id);
    void updateEstadoSistema(Integer id, String estadoSistema);
}