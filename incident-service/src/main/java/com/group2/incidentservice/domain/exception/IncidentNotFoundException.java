package com.group2.incidentservice.domain.exception;

public class IncidentNotFoundException extends RuntimeException {
    public IncidentNotFoundException(String id) {
        super("Incidente con ID " + id + " no encontrado");
    }
}
