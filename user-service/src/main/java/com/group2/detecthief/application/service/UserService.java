package com.group2.detecthief.application.service;

import com.group2.detecthief.api.dto.UserRequestDTO;
import com.group2.detecthief.api.dto.UserResponseDTO;
import com.group2.detecthief.application.mapper.UserMapper;
import com.group2.detecthief.domain.exception.UserNotFoundException;
import com.group2.detecthief.domain.model.User;
import com.group2.detecthief.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        // Validaciones de negocio
        if (userRepository.existsByEmail(userRequestDTO.email())) {
            throw new IllegalArgumentException("Email ya registrado");
        }

        if (userRepository.existsByUsername(userRequestDTO.username())) {
            throw new IllegalArgumentException("Nombre de usuario ya registrado");
        }

        // Crear usuario de dominio
        User user = userMapper.toModel(userRequestDTO);

        // Guardar en repositorio
        User savedUser = userRepository.save(user);

        // Mapear y devolver respuesta
        return userMapper.toResponseDTO(savedUser);
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

    public UserResponseDTO updateUser(UUID id, UserRequestDTO userRequestDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));

        // Actualizamos el usuario con los nuevos datos
        existingUser.update(
                userRequestDTO.username(),
                userRequestDTO.email(),
                userRequestDTO.firstName(),
                userRequestDTO.lastName()
        );

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toResponseDTO(updatedUser);
    }

    public void deleteUser(UUID id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new UserNotFoundException(id.toString());
        }
        userRepository.deleteById(id);
    }

    /*public UserResponseDTO activateUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
        user.activate();
        User updatedUser = userRepository.save(user);
        return userMapper.toResponseDTO(updatedUser);
    }

    public UserResponseDTO deactivateUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
        user.deactivate();
        User updatedUser = userRepository.save(user);
        return userMapper.toResponseDTO(updatedUser);
    }*/
}