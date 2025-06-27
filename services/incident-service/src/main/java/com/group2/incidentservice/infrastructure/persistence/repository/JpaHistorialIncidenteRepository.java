package com.group2.incidentservice.infrastructure.persistence.repository;

import com.group2.incidentservice.infrastructure.persistence.entity.HistorialIncidenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaHistorialIncidenteRepository extends JpaRepository<HistorialIncidenteEntity, Integer> {
}