package com.group2.incidentservice.infrastructure.persistence.repository;

import com.group2.incidentservice.infrastructure.persistence.entity.TipoIncidenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTipoIncidenteRepository extends JpaRepository<TipoIncidenteEntity, Integer> {
}