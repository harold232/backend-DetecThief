package com.group2.incidentservice.infrastructure.config;

import com.group2.incidentservice.domain.repository.IncidentRepository;
import com.group2.incidentservice.infrastructure.persistence.adapter.IncidentRepositoryAdapter;
import com.group2.incidentservice.infrastructure.persistence.repository.JpaIncidentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public IncidentRepository incidentRepository(JpaIncidentRepository jpaIncidentRepository) {
        return new IncidentRepositoryAdapter(jpaIncidentRepository);
    }
}