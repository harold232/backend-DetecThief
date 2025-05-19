package com.group2.detecthief.infrastructure.persistence.adapter;

import com.group2.detecthief.domain.model.User;
import com.group2.detecthief.domain.repository.UserRepository;
import com.group2.detecthief.infrastructure.persistence.entity.UserEntity;
import com.group2.detecthief.infrastructure.persistence.repository.JpaUserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserRepositoryAdapter implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryAdapter(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = toEntity(user);
        UserEntity savedEntity = jpaUserRepository.save(entity);
        return toModel(savedEntity);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return jpaUserRepository.findById(id).map(this::toModel);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email).map(this::toModel);
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll().stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        jpaUserRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return jpaUserRepository.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return jpaUserRepository.existsByNickname(nickname);
    }

    // Conversion methods
    private UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setNickname(user.getNickname());
        entity.setEmail(user.getEmail());
        entity.setNombre(user.getNombre());
        entity.setApellido(user.getApellido());
        entity.setRol(user.getRol());
        entity.setEstadoSistema(user.getEstadoSistema());
        entity.setCreadoEn(user.getCreadoEn());
        entity.setActualizadoEn(user.getActualizadoEn());
        return entity;
    }

    private User toModel(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getNickname(),
                entity.getEmail(),
                entity.getNombre(),
                entity.getApellido(),
                entity.getRol(),
                entity.getEstadoSistema(),
                entity.getCreadoEn(),
                entity.getActualizadoEn()
        );
    }
}