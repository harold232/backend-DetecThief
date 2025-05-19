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
    public UserResponseDTO updateUserByEmail(String email, UserRequestDTO userRequestDTO) {
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con email: " + email));

        if (!existingUser.getEmail().equals(userRequestDTO.email()) &&
                userRepository.existsByEmail(userRequestDTO.email())) {
            throw new IllegalArgumentException("Email ya registrado");
        }

        if (!existingUser.getNickname().equals(userRequestDTO.nickname()) &&
                userRepository.existsByNickname(userRequestDTO.nickname())) {
            throw new IllegalArgumentException("Nickname ya registrado");
        }

        existingUser.update(
                userRequestDTO.nickname(),
                userRequestDTO.email(),
                userRequestDTO.nombre(),
                userRequestDTO.apellido()
        );

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toResponseDTO(updatedUser);
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByEmail(userRequestDTO.email())) {
            throw new IllegalArgumentException("Email ya registrado");
        }
        if (userRepository.existsByNickname(userRequestDTO.nickname())) {
            throw new IllegalArgumentException("Nickname ya registrado");
        }
        // Crear usuario de dominio
        User user = userMapper.toModel(userRequestDTO);
        // Guardar en repositorio
        User savedUser = userRepository.save(user);
        // Mapear y devolver respuesta
        return userMapper.toResponseDTO(savedUser);
    }

    public UserResponseDTO getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
        return userMapper.toResponseDTO(user);
    }

    public UserResponseDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con email: " + email));
        return userMapper.toResponseDTO(user);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO updateUser(Integer id, UserRequestDTO userRequestDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
        // Verificar si el email ya está en uso por otro usuario
        if (!existingUser.getEmail().equals(userRequestDTO.email()) &&
                userRepository.existsByEmail(userRequestDTO.email())) {
            throw new IllegalArgumentException("Email ya registrado");
        }
        // Verificar si el nickname ya está en uso por otro usuario
        if (!existingUser.getNickname().equals(userRequestDTO.nickname()) &&
                userRepository.existsByNickname(userRequestDTO.nickname())) {
            throw new IllegalArgumentException("Nickname ya registrado");
        }
        // Actualizamos el usuario con los nuevos datos
        existingUser.update(
                userRequestDTO.nickname(),
                userRequestDTO.email(),
                userRequestDTO.nombre(),
                userRequestDTO.apellido()
        );
        User updatedUser = userRepository.save(existingUser);
        return userMapper.toResponseDTO(updatedUser);
    }

    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id.toString());
        }
        userRepository.deleteById(id);
    }

    public UserResponseDTO activateUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
        user.activate();
        User updatedUser = userRepository.save(user);
        return userMapper.toResponseDTO(updatedUser);
    }

    public UserResponseDTO deactivateUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
        user.deactivate();
        User updatedUser = userRepository.save(user);
        return userMapper.toResponseDTO(updatedUser);
    }
}