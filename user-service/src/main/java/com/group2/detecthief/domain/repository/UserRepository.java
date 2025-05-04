package com.group2.detecthief.domain.repository;

import com.group2.detecthief.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(UUID id);
    List<User> findAll();
    void deleteById(UUID id);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}