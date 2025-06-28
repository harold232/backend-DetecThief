package com.group2.incidentservice.infrastructure.config;

import com.group2.incidentservice.domain.repository.IncidentRepository;
import com.group2.incidentservice.domain.repository.TipoIncidenteRepository;
import com.group2.incidentservice.domain.repository.HistorialIncidenteRepository;
import com.group2.incidentservice.infrastructure.persistence.adapter.IncidentRepositoryAdapter;
import com.group2.incidentservice.infrastructure.persistence.adapter.TipoIncidenteRepositoryAdapter;
import com.group2.incidentservice.infrastructure.persistence.adapter.HistorialIncidenteRepositoryAdapter;
import com.group2.incidentservice.infrastructure.persistence.repository.JpaIncidentRepository;
import com.group2.incidentservice.infrastructure.persistence.repository.JpaTipoIncidenteRepository;
import com.group2.incidentservice.infrastructure.persistence.repository.JpaHistorialIncidenteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfiguration {

    @Bean
    public IncidentRepository incidentRepository(JpaIncidentRepository jpaIncidentRepository) {
        return new IncidentRepositoryAdapter(jpaIncidentRepository);
    }

    @Bean
    public TipoIncidenteRepository tipoIncidenteRepository(JpaTipoIncidenteRepository jpaTipoIncidenteRepository) {
        return new TipoIncidenteRepositoryAdapter(jpaTipoIncidenteRepository);
    }

    @Bean
    public HistorialIncidenteRepository historialIncidenteRepository(JpaHistorialIncidenteRepository jpaHistorialIncidenteRepository) {
        return new HistorialIncidenteRepositoryAdapter(jpaHistorialIncidenteRepository);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}