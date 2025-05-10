package com.group2.detecthief.api.controller;

import com.group2.detecthief.api.dto.LoginRequestDTO;
import com.group2.detecthief.api.dto.UserResponseDTO;
import com.group2.detecthief.application.service.UserService;
import com.group2.detecthief.domain.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            UserResponseDTO response = userService.authenticateUser(
                    loginRequestDTO.username(),
                    loginRequestDTO.password()
            );
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<UserResponseDTO> changePassword(
            @RequestParam UUID userId,
            @RequestParam String currentPassword,
            @RequestParam String newPassword) {
        try {
            UserResponseDTO response = userService.changePassword(userId, currentPassword, newPassword);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}