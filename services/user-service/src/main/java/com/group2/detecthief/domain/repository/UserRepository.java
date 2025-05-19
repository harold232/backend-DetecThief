package com.group2.detecthief.domain.repository;

import com.group2.detecthief.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);

    Optional<User> findById(Integer id);

    Optional<User> findByEmail(String email);

    List<User> findAll();

    void deleteById(Integer id);

    boolean existsById(Integer id);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}