package com.group2.detecthief.api.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthResponseDTO(
    @JsonProperty("access_token") String accessToken,
    @JsonProperty("refresh_token") String refreshToken,
    @JsonProperty("token_type") String tokenType,
    @JsonProperty("expires_in") int expiresIn,
    @JsonProperty("expires_at") long expiresAt,
    @JsonProperty("user") User user
) {
    public record User(
        String id,
        String email,
        String role,
        @JsonProperty("email_confirmed_at") ZonedDateTime emailConfirmedAt,
        @JsonProperty("is_anonymous") boolean isAnonymous
    ) {}
}