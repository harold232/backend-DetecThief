package com.group2.detecthief.application.service;

import com.group2.detecthief.api.dto.UserRequestDTO;
import com.group2.detecthief.api.dto.UserResponseDTO;
import com.group2.detecthief.application.mapper.UserMapper;
import com.group2.detecthief.application.seguridad.PasswordEncoder;
import com.group2.detecthief.domain.exception.UserNotFoundException;
import com.group2.detecthief.domain.model.User;
import com.group2.detecthief.domain.model.UserProfile;
import com.group2.detecthief.domain.repository.UserProfileRepository;
import com.group2.detecthief.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserProfileRepository userProfileRepository,
                       UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // Método para autenticar a un usuario (login)
    public UserResponseDTO authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        return userMapper.toResponseDTO(user);
    }

    // Método para cambiar la contraseña
    public UserResponseDTO changePassword(UUID id, String currentPassword, String newPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new IllegalArgumentException("Contraseña actual incorrecta");
        }

        user.updatePassword(passwordEncoder.encode(newPassword));
        User updatedUser = userRepository.save(user);

        return userMapper.toResponseDTO(updatedUser);
    }
    public UserResponseDTO getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
        return userMapper.toResponseDTO(user);
    }
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    // Los métodos existentes siguen iguales...
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByEmail(userRequestDTO.email())) {
            throw new IllegalArgumentException("Email ya registrado");
        }

        if (userRepository.existsByUsername(userRequestDTO.username())) {
            throw new IllegalArgumentException("Nombre de usuario ya registrado");
        }

        // Crear usuario de dominio (el password ya se encripta en el mapper)
        User user = userMapper.toModel(userRequestDTO);

        // Guardar en repositorio
        User savedUser = userRepository.save(user);

        // Mapear y devolver respuesta
        return userMapper.toResponseDTO(savedUser);
    }

    // Se actualiza para manejar actualizaciones sin cambiar la contraseña
    public UserResponseDTO updateUser(UUID id, UserRequestDTO userRequestDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));

        // Actualizamos el usuario con los nuevos datos (sin la contraseña)
        existingUser.update(
                userRequestDTO.username(),
                userRequestDTO.email(),
                userRequestDTO.firstName(),
                userRequestDTO.lastName()
        );

        // Si se proporciona una nueva contraseña, la actualizamos
        if (userRequestDTO.password() != null && !userRequestDTO.password().isEmpty()) {
            existingUser.updatePassword(passwordEncoder.encode(userRequestDTO.password()));
        }

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toResponseDTO(updatedUser);
    }

    public void deleteUser(UUID id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new UserNotFoundException(id.toString());
        }
        userRepository.deleteById(id);
    }

    public UserResponseDTO activateUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));

        UserProfile userProfile = userProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Perfil de usuario no encontrado para el ID proporcionado"));

        userProfile.activate();
        //UserProfile updateUserProfile = userProfileRepository.save(userProfile);
        userProfileRepository.save(userProfile);

        return userMapper.toResponseDTO(user);
    }

    public UserResponseDTO deactivateUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));

        UserProfile userProfile = userProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Perfil de usuario no encontrado para el ID proporcionado"));

        userProfile.deactivate();
        //UserProfile updateUserProfile = userProfileRepository.save(userProfile);
        userProfileRepository.save(userProfile);

        return userMapper.toResponseDTO(user);
    }
}