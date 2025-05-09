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
        if (dto.role() != null && !dto.role().isEmpty()) {
            return new User(
                    dto.username(),
                    dto.email(),
                    dto.firstName(),
                    dto.lastName(),
                    dto.role()
            );
        } else {
            User user = new User(
                    dto.username(),
                    dto.email(),
                    dto.firstName(),
                    dto.lastName()
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
                    user.getProfile().getStateSystem(),
                    user.getProfile().isActive(),
                    user.getProfile().getCreatedAt(),
                    user.getProfile().getUpdatedAt()
            );
        }
        
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                profileDTO
        );
    }
}