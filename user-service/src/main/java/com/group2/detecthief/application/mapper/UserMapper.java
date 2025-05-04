package com.group2.detecthief.application.mapper;

import com.group2.detecthief.api.dto.UserProfileDTO;
import com.group2.detecthief.api.dto.UserRequestDTO;
import com.group2.detecthief.api.dto.UserResponseDTO;
import com.group2.detecthief.domain.model.User;
import com.group2.detecthief.domain.model.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toModel(UserRequestDTO dto) {
        if (dto.getRole() != null && !dto.getRole().isEmpty()) {
            return new User(
                    dto.getUsername(),
                    dto.getEmail(),
                    dto.getFirstName(),
                    dto.getLastName(),
                    dto.getRole()
            );
        } else {
            User user = new User(
                    dto.getUsername(),
                    dto.getEmail(),
                    dto.getFirstName(),
                    dto.getLastName()
            );
            user.setProfile(new UserProfile(null, "USER", true));
            return user;
        }
    }

    public UserResponseDTO toResponseDTO(User user) {
        UserProfileDTO profileDTO = null;
        if (user.getProfile() != null) {
            profileDTO = new UserProfileDTO(
                    user.getProfile().getId(),
                    user.getProfile().getRole(),
                    user.getProfile().getCreatedAt(),
                    user.getProfile().getUpdatedAt(),
                    user.getProfile().getStateSystem()
            );
        }

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.isActive(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                profileDTO
        );
    }
}