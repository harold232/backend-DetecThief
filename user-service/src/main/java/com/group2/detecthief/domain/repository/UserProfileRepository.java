package com.group2.detecthief.domain.repository;

import com.group2.detecthief.domain.model.UserProfile;

import java.util.Optional;
import java.util.UUID;

public interface UserProfileRepository {
    UserProfile save(UserProfile userProfile);
    Optional<UserProfile> findByUserId(UUID id);
}