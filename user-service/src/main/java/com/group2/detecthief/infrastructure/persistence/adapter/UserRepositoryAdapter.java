package com.group2.detecthief.infrastructure.persistence.adapter;

import com.group2.detecthief.domain.model.User;
import com.group2.detecthief.domain.model.UserProfile;
import com.group2.detecthief.domain.repository.UserRepository;
import com.group2.detecthief.infrastructure.persistence.entity.UserEntity;
import com.group2.detecthief.infrastructure.persistence.entity.UserProfileEntity;
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
    public Optional<User> findById(UUID id) {
        return jpaUserRepository.findById(id).map(this::toModel);
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll().stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        jpaUserRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaUserRepository.existsByUsername(username);
    }

    // Update toModel method to include profile conversion
    private User toModel(UserEntity entity) {
        User user = new User(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
        if (entity.getProfile() != null) {
            UserProfileEntity profileEntity = entity.getProfile();
            UserProfile profile = new UserProfile(
                profileEntity.getId(),
                profileEntity.getRole(),
                profileEntity.getStateSystem(),
                profileEntity.isActive(),
                profileEntity.getCreatedAt(),
                profileEntity.getUpdatedAt(),
                user
            );
            user.setProfile(profile);
        }

        return user;
    }

    // Update toEntity method to include profile conversion
    private UserEntity toEntity(User model) {
        UserEntity entity = new UserEntity(
                model.getId(),
                model.getUsername(),
                model.getEmail(),
                model.getFirstName(),
                model.getLastName(),
                model.getCreatedAt(),
                model.getUpdatedAt()
        );

        // Convert profile if it exists
        if (model.getProfile() != null) {
            UserProfile profile = model.getProfile();
            UserProfileEntity profileEntity = new UserProfileEntity(
                    profile.getId(),
                    entity,
                    profile.getRole(),
                    profile.getCreatedAt(),
                    profile.getUpdatedAt(),
                    profile.getStateSystem()
            );
            entity.setProfile(profileEntity);
        }

        return entity;
    }
}