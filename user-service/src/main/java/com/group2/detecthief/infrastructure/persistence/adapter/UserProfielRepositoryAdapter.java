package com.group2.detecthief.infrastructure.persistence.adapter;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.group2.detecthief.domain.model.User;
import com.group2.detecthief.domain.model.UserProfile;
import com.group2.detecthief.domain.repository.UserProfileRepository;
import com.group2.detecthief.infrastructure.persistence.entity.UserEntity;
import com.group2.detecthief.infrastructure.persistence.entity.UserProfileEntity;
import com.group2.detecthief.infrastructure.persistence.repository.JpaUserProfileRepository;

@Component
public class UserProfielRepositoryAdapter implements UserProfileRepository {

    private final JpaUserProfileRepository jpaUserProfileRepository;

    public UserProfielRepositoryAdapter(JpaUserProfileRepository jpaUserProfileRepository) {
        this.jpaUserProfileRepository = jpaUserProfileRepository;
    }

    @Override
    public UserProfile save(UserProfile userProfile) {
        UserProfileEntity entity = toEntity(userProfile);
        UserProfileEntity savedEntity = jpaUserProfileRepository.save(entity);
        return toModel(savedEntity);
    }

    @Override
    public Optional<UserProfile> findByUserId(UUID id) {
        return jpaUserProfileRepository.findByUserId(id).map(this::toModel);
    }

    private UserProfileEntity toEntity(UserProfile userProfile) {
        if (userProfile == null) {
            return null;
        }
        UserProfileEntity entity = new UserProfileEntity();
        entity.setId(userProfile.getId());
        entity.setRole(userProfile.getRole());
        entity.setStateSystem(userProfile.getStateSystem());
        entity.setActive(userProfile.isActive());
        entity.setCreatedAt(userProfile.getCreatedAt());
        entity.setUpdatedAt(userProfile.getUpdatedAt());

        // Convert user if it exists
        if (userProfile.getUser() != null) {
            User user = userProfile.getUser();
            UserEntity userEntity = new UserEntity();
            userEntity.setId(user.getId());
            userEntity.setUsername(user.getUsername());
            userEntity.setEmail(user.getEmail());
            userEntity.setFirstName(user.getFirstName());
            userEntity.setLastName(user.getLastName());
            userEntity.setCreatedAt(user.getCreatedAt());
            userEntity.setUpdatedAt(user.getUpdatedAt());
            entity.setUser(userEntity);
        }

        return entity;
    }

    private UserProfile toModel(UserProfileEntity entity) {
        if (entity == null) {
            return null;
        }

        User user = null;
        if (entity.getUser() != null) {
            UserEntity userEntity = entity.getUser();
            user = new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt()
            );
        }

        return new UserProfile(
            entity.getId(),
            entity.getRole(),
            entity.getStateSystem(),
            entity.isActive(),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            user
        );
    }
}
