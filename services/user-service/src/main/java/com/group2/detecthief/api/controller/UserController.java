package com.group2.detecthief.api.controller;

import com.group2.detecthief.api.dto.UserRequestDTO;
import com.group2.detecthief.api.dto.UserResponseDTO;
import com.group2.detecthief.application.service.UserService;
import com.group2.detecthief.domain.exception.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        try {
            UserResponseDTO response = userService.createUser(userRequestDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // Add this new endpoint for checking email existence
    @GetMapping("/check-email")
    public ResponseEntity<Map<String, Boolean>> checkEmailExists(@RequestParam String email) {
        boolean exists = userService.existsByEmail(email);
        Map<String, Boolean> response = Map.of("exists", exists);

        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email no encontrado");
        }

        return ResponseEntity.ok(response);
    }

    // Get user by email - for authentication
    @GetMapping("/by-email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {
        try {
            UserResponseDTO response = userService.getUserByEmail(email);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @PutMapping("/by-email/{email}")
    public ResponseEntity<UserResponseDTO> updateUserByEmail(
            @PathVariable String email,
            @Valid @RequestBody UserRequestDTO userRequestDTO) {
        try {
            UserResponseDTO response = userService.updateUserByEmail(email, userRequestDTO);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // Keep your existing endpoints...
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Integer id) {
        try {
            UserResponseDTO response = userService.getUserById(id);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Integer id,
            @Valid @RequestBody UserRequestDTO userRequestDTO) {
        try {
            UserResponseDTO response = userService.updateUser(id, userRequestDTO);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<UserResponseDTO> activateUser(@PathVariable Integer id) {
        try {
            UserResponseDTO response = userService.activateUser(id);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<UserResponseDTO> deactivateUser(@PathVariable Integer id) {
        try {
            UserResponseDTO response = userService.deactivateUser(id);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}