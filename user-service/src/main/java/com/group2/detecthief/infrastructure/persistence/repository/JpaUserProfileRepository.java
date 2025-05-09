package com.group2.detecthief.infrastructure.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group2.detecthief.infrastructure.persistence.entity.UserProfileEntity;

public interface JpaUserProfileRepository extends JpaRepository<UserProfileEntity, UUID> {
    Optional<UserProfileEntity> findByUserId(UUID id);
}
