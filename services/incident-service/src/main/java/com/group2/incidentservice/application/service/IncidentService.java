package com.group2.incidentservice.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.group2.incidentservice.application.mapper.IncidentMapper;
import com.group2.incidentservice.domain.model.Incident;
import com.group2.incidentservice.domain.repository.IncidentRepository;

@Service
public class IncidentService {
    private final IncidentRepository incidentRepository;
    private final IncidentMapper incidentMapper;

    public IncidentService(IncidentRepository incidentRepository, IncidentMapper incidentMapper) {
        this.incidentRepository = incidentRepository;
        this.incidentMapper = incidentMapper;
    }

    public Incident createIncident(Incident incident) {
        return incidentRepository.save(incident);
    }

    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    public Incident getIncidentById(Integer id) {
        return incidentRepository.findById(id).orElse(null);
    }

    public Incident updateIncident(Integer id, Incident incident) {
        if (incidentRepository.existsById(id)) {
            incident.setId(id);
            return incidentRepository.save(incident);
        }
        return null;
    }

    public void deleteIncident(Integer id) {
        incidentRepository.deleteById(id);
    }

}