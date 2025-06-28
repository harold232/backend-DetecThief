package com.group2.incidentservice.application.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.group2.incidentservice.api.dto.IncidentWithTypeDTO;
import com.group2.incidentservice.application.mapper.IncidentMapper;
import com.group2.incidentservice.domain.model.Incident;
import com.group2.incidentservice.domain.model.TipoIncidente;
import com.group2.incidentservice.domain.model.HistorialIncidente;
import com.group2.incidentservice.domain.repository.IncidentRepository;
import com.group2.incidentservice.domain.repository.TipoIncidenteRepository;
import com.group2.incidentservice.domain.repository.HistorialIncidenteRepository;
import com.group2.incidentservice.domain.exception.IncidentNotFoundException;

@Service
public class IncidentService {
    private final IncidentRepository incidentRepository;
    private final TipoIncidenteRepository tipoIncidenteRepository;
    private final HistorialIncidenteRepository historialIncidenteRepository;
    private final IncidentMapper incidentMapper;

    public IncidentService(IncidentRepository incidentRepository,
                           TipoIncidenteRepository tipoIncidenteRepository,
                           HistorialIncidenteRepository historialIncidenteRepository,
                           IncidentMapper incidentMapper) {
        this.incidentRepository = incidentRepository;
        this.tipoIncidenteRepository = tipoIncidenteRepository;
        this.historialIncidenteRepository = historialIncidenteRepository;
        this.incidentMapper = incidentMapper;
    }

    public Incident createIncident(Incident incident) {
        return incidentRepository.save(incident);
    }

    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    public List<IncidentWithTypeDTO> getAllIncidentsWithType() {
        List<Incident> incidents = incidentRepository.findAll();
        return incidents.stream()
                .map(this::mapToIncidentWithTypeDTO)
                .toList();
    }

    public IncidentWithTypeDTO getIncidentWithTypeById(Integer id) {
        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new IncidentNotFoundException(id));
        return mapToIncidentWithTypeDTO(incident);
    }

    public Incident getIncidentById(Integer id) {
        return incidentRepository.findById(id)
                .orElseThrow(() -> new IncidentNotFoundException(id));
    }

    public Incident updateIncident(Integer id, Incident incident) {
        if (incidentRepository.existsById(id)) {
            incident.setId(id);
            return incidentRepository.save(incident);
        }
        throw new IncidentNotFoundException(id);
    }

    public void deleteIncident(Integer id) {
        if (!incidentRepository.existsById(id)) {
            throw new IncidentNotFoundException(id);
        }
        incidentRepository.deleteById(id);
    }

    @Transactional
    public void confirmIncident(Integer id) {
        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new IncidentNotFoundException(id));

        // Create history record - DON'T use the incident ID as FK reference
        HistorialIncidente historial = new HistorialIncidente();
        historial.setComentario("Incident ID: " + id + " - " + incident.getDescripcion()); // Include ID in comment
        historial.setFechaCambio(LocalDateTime.now());
        historial.setContactosNotificados("");
        historial.setEstadoSistema("activo");

        historialIncidenteRepository.save(historial);

        // Now delete from incidents table
        incidentRepository.deleteById(id);
    }

    public void rejectIncident(Integer id) {
        if (!incidentRepository.existsById(id)) {
            throw new IncidentNotFoundException(id);
        }
        incidentRepository.deleteById(id);
    }

    public void deleteHistorialIncident(Integer id) {
        historialIncidenteRepository.deleteById(id);
    }

    public List<HistorialIncidente> getAllHistorialIncidentes() {
        return historialIncidenteRepository.findAll();
    }

    public HistorialIncidente getHistorialIncidenteById(Integer id) {
        return historialIncidenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historial incidente not found with id: " + id));
    }

    public void updateIncidentStatus(Integer id, String nuevoEstado) {
        incidentRepository.findById(id).ifPresent(incident -> {
            incident.setEstado(nuevoEstado);
            incident.setActualizadoEn(LocalDateTime.now());
            incidentRepository.save(incident);
        });
    }

    private IncidentWithTypeDTO mapToIncidentWithTypeDTO(Incident incident) {
        Optional<TipoIncidente> tipoIncidente = tipoIncidenteRepository.findById(incident.getTipoIncidentId());

        return new IncidentWithTypeDTO(
                incident.getId(),
                incident.getCameraId(),
                incident.getTipoIncidentId(),
                tipoIncidente.map(TipoIncidente::getNombre).orElse("Unknown"),
                tipoIncidente.map(TipoIncidente::getGravedad).orElse(0),
                incident.getEstado(),
                incident.getDescripcion(),
                incident.getFechaDetectado(),
                incident.getImagenReferencia(),
                incident.getEstadoSistema(),
                incident.getCreadoEn(),
                incident.getActualizadoEn()
        );
    }
}