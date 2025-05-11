package com.group2.detecthief.api.controller;

import com.group2.detecthief.api.dto.AuthRequestDTO;
import com.group2.detecthief.api.dto.AuthResponseDTO;
import com.group2.detecthief.api.dto.AuthResponseUserDTO;
import com.group2.detecthief.application.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody AuthRequestDTO authRequestDTO) {
        AuthResponseDTO response = authService.register(authRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthRequestDTO authRequestDTO) {
        AuthResponseDTO response = authService.login(authRequestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<AuthResponseUserDTO> getCurrentUser(@RequestHeader("Authorization") String token) {
        AuthResponseUserDTO response = authService.getCurrentUser(token);
        return ResponseEntity.ok(response);
    }
}