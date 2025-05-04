package com.group2.detecthief.application.mapper;

import com.group2.detecthief.api.dto.UserRequestDTO;
import com.group2.detecthief.api.dto.UserResponseDTO;
import com.group2.detecthief.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toModel(UserRequestDTO dto) {
        return new User(
                dto.getUsername(),
                dto.getEmail(),
                dto.getFirstName(),
                dto.getLastName()
        );
    }

    public UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.isActive(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}