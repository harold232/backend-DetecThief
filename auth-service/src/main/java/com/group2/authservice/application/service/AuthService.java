package com.group2.authservice.application.service;

import com.group2.authservice.api.dto.AuthRequestDTO;
import com.group2.authservice.api.dto.AuthResponseDTO;
import com.group2.authservice.api.dto.AuthResponseUserDTO;
import com.group2.authservice.infrastructure.supabase.SupabaseAuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private SupabaseAuthClient supabaseAuthClient;

    public AuthResponseDTO register(AuthRequestDTO authRequestDTO) {
        return supabaseAuthClient.register(authRequestDTO);
    }

    public AuthResponseDTO login(AuthRequestDTO authRequestDTO) {
        return supabaseAuthClient.login(authRequestDTO);
    }

    public AuthResponseUserDTO getCurrentUser(String token) {
        return supabaseAuthClient.getCurrentUser(token);
    }
}