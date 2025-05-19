package com.group2.incidentservice.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group2.incidentservice.application.service.IncidentService;
import com.group2.incidentservice.domain.model.Incident;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {
    
    @Autowired
    private IncidentService incidentService;

    @PostMapping
    public ResponseEntity<Incident> createIncident(@RequestBody Incident incident) {
        Incident createdIncident = incidentService.createIncident(incident);
        return ResponseEntity.ok(createdIncident);
    }

    @GetMapping
    public ResponseEntity<List<Incident>> getAllIncidents() {
        List<Incident> incidents = incidentService.getAllIncidents();
        return ResponseEntity.ok(incidents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incident> getIncidentById(@PathVariable Integer id) {
        Incident incident = incidentService.getIncidentById(id);
        return ResponseEntity.ok(incident);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Incident> updateIncident(@PathVariable Integer id, @RequestBody Incident incident) {
        Incident updatedIncident = incidentService.updateIncident(id, incident);
        return ResponseEntity.ok(updatedIncident);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable Integer id) {
        incidentService.deleteIncident(id);
        return ResponseEntity.noContent().build();
    }

}
