package com.group2.incidentservice.domain.repository;

import com.group2.incidentservice.domain.model.TipoIncidente;
import java.util.Optional;

public interface TipoIncidenteRepository {
    Optional<TipoIncidente> findById(Integer id);
}

