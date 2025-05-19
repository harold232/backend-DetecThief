package com.group2.authservice.infrastructure.supabase;

import com.group2.authservice.api.dto.AuthRequestDTO;
import com.group2.authservice.api.dto.AuthResponseDTO;
import com.group2.authservice.api.dto.AuthResponseUserDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SupabaseAuthClient {

    private final String SUPABASE_URL = System.getProperty("SUPABASE_URL");
    private final String SUPABASE_API_KEY = System.getProperty("SUPABASE_API_KEY");

    private final RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", SUPABASE_API_KEY);
        return headers;
    }

    public AuthResponseDTO register(AuthRequestDTO authRequestDTO) {
        String url = SUPABASE_URL + "/auth/v1/signup";

        HttpHeaders headers = createHeaders();
        HttpEntity<AuthRequestDTO> requestEntity = new HttpEntity<>(authRequestDTO, headers);

        ResponseEntity<AuthResponseDTO> response = restTemplate.postForEntity(url, requestEntity,
                AuthResponseDTO.class);
        return response.getBody();
    }

    public AuthResponseDTO login(AuthRequestDTO authRequestDTO) {
        String url = SUPABASE_URL + "/auth/v1/token?grant_type=password";

        HttpHeaders headers = createHeaders();
        HttpEntity<AuthRequestDTO> requestEntity = new HttpEntity<>(authRequestDTO, headers);

        ResponseEntity<AuthResponseDTO> response = restTemplate.postForEntity(url, requestEntity,
                AuthResponseDTO.class);
        return response.getBody();
    }

    public AuthResponseUserDTO getCurrentUser(String token) {
        String url = SUPABASE_URL + "/auth/v1/user";

        HttpHeaders headers = createHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<AuthResponseUserDTO> response = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                requestEntity,
                AuthResponseUserDTO.class);

        return response.getBody();
    }
}