package com.group2.detecthief.infrastructure.persistence.repository;

import com.group2.detecthief.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    Optional<UserEntity> findByEmail(String email);
}