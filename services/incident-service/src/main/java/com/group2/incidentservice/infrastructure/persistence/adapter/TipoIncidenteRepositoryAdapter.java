package com.group2.incidentservice.infrastructure.persistence.adapter;

import com.group2.incidentservice.domain.model.TipoIncidente;
import com.group2.incidentservice.domain.repository.TipoIncidenteRepository;
import com.group2.incidentservice.infrastructure.persistence.entity.TipoIncidenteEntity;
import com.group2.incidentservice.infrastructure.persistence.repository.JpaTipoIncidenteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class TipoIncidenteRepositoryAdapter implements TipoIncidenteRepository {
    private final JpaTipoIncidenteRepository jpaTipoIncidenteRepository;

    @Override
    public Optional<TipoIncidente> findById(Integer id) {
        return jpaTipoIncidenteRepository.findById(id).map(this::toModel);
    }

    private TipoIncidente toModel(TipoIncidenteEntity entity) {
        return new TipoIncidente(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getGravedad(),
                entity.getEstadoSistema(),
                entity.getCreadoEn(),
                entity.getActualizadoEn()
        );
    }
}