package com.group2.incidentservice.application.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.group2.incidentservice.api.dto.HistorialIncidenteWithTypeDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import com.group2.incidentservice.api.dto.IncidentWithTypeDTO;
import com.group2.incidentservice.api.dto.UserDTO;
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
    private final RestTemplate restTemplate;

    // Retry configuration
    private static final int MAX_RETRY_ATTEMPTS = 3;
    private static final long RETRY_DELAY_MS = 1000; // 1 second

    public IncidentService(IncidentRepository incidentRepository,
                           TipoIncidenteRepository tipoIncidenteRepository,
                           HistorialIncidenteRepository historialIncidenteRepository,
                           IncidentMapper incidentMapper,
                           RestTemplate restTemplate) {
        this.incidentRepository = incidentRepository;
        this.tipoIncidenteRepository = tipoIncidenteRepository;
        this.historialIncidenteRepository = historialIncidenteRepository;
        this.incidentMapper = incidentMapper;
        this.restTemplate = restTemplate;
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

        // Get active operators for notifications with retry logic
        String contactosNotificados = getActiveOperatorIdsWithRetry();

        // Create history record
        HistorialIncidente historial = new HistorialIncidente();
        historial.setComentario(incident.getDescripcion());
        historial.setFechaCambio(LocalDateTime.now());
        historial.setContactosNotificados(contactosNotificados);
        historial.setEstadoSistema("activo");
        historial.setEvidencia_referencial(incident.getImagenReferencia());
        historial.setTipoIncidentId(incident.getTipoIncidentId());


        historialIncidenteRepository.save(historial);

        // Delete from incidents table
        incidentRepository.deleteById(id);
    }
    public List<HistorialIncidenteWithTypeDTO> getAllHistorialIncidentesWithType() {
        List<HistorialIncidente> historialList = historialIncidenteRepository.findAll();
        return historialList.stream()
                .map(this::mapToHistorialIncidenteWithTypeDTO)
                .toList();
    }

    public HistorialIncidenteWithTypeDTO getHistorialIncidenteWithTypeById(Integer id) {
        HistorialIncidente historial = historialIncidenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historial incidente not found with id: " + id));
        return mapToHistorialIncidenteWithTypeDTO(historial);
    }

    private HistorialIncidenteWithTypeDTO mapToHistorialIncidenteWithTypeDTO(HistorialIncidente historial) {
        Optional<TipoIncidente> tipoIncidente = tipoIncidenteRepository.findById(historial.getTipoIncidentId());

        return new HistorialIncidenteWithTypeDTO(
                historial.getId(),
                historial.getComentario(),
                historial.getFechaCambio(),
                historial.getContactosNotificados(),
                historial.getEstadoSistema(),
                historial.getEvidencia_referencial(),
                historial.getTipoIncidentId(),
                tipoIncidente.map(TipoIncidente::getNombre).orElse("Unknown"),
                tipoIncidente.map(TipoIncidente::getDescripcion).orElse("Unknown"),
                tipoIncidente.map(TipoIncidente::getGravedad).orElse(0)
        );
    }
    public void updateHistorialEstadoSistema(Integer id, String estadoSistema) {
        // Validate estado_sistema values
        if (!isValidEstadoSistema(estadoSistema)) {
            throw new IllegalArgumentException("Estado sistema inválido: " + estadoSistema);
        }

        historialIncidenteRepository.updateEstadoSistema(id, estadoSistema);
    }

    /**
     * Get active operator IDs with retry logic
     */
    private String getActiveOperatorIdsWithRetry() {
        for (int attempt = 1; attempt <= MAX_RETRY_ATTEMPTS; attempt++) {
            try {
                String result = getActiveOperatorIds();
                System.out.println("Successfully retrieved operators on attempt " + attempt);
                return result;

            } catch (RestClientException e) {
                System.err.println("Attempt " + attempt + " failed to fetch users: " + e.getMessage());

                if (attempt == MAX_RETRY_ATTEMPTS) {
                    System.err.println("All retry attempts failed. Returning empty contacts list.");
                    return "";
                }

                // Wait before retrying
                try {
                    Thread.sleep(RETRY_DELAY_MS);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    System.err.println("Retry interrupted. Returning empty contacts list.");
                    return "";
                }
            }
        }
        return "";
    }

    /**
     * Original method to get active operator IDs
     */
    private String getActiveOperatorIds() {
        String url = "http://localhost:8080/api/usuarios";
        UserDTO[] users = restTemplate.getForObject(url, UserDTO[].class);

        if (users == null) {
            return "";
        }

        List<String> operatorIds = new ArrayList<>();
        for (UserDTO user : users) {
            if ("operador".equals(user.rol()) &&
                    "activo".equals(user.estadoNotificaciones())) {
                operatorIds.add(user.id().toString());
            }
        }

        return String.join(",", operatorIds);
    }

    private boolean isValidEstadoSistema(String estadoSistema) {
        return "activo".equals(estadoSistema) ||
                "confirmado".equals(estadoSistema) ||
                "descartado".equals(estadoSistema);
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