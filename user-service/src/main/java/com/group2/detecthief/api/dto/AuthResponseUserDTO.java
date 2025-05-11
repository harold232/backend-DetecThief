package com.group2.detecthief.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

public record AuthResponseUserDTO(
    String id,
    String aud,
    String role,
    String email,
    @JsonProperty("email_confirmed_at") ZonedDateTime emailConfirmedAt,
    String phone,
    @JsonProperty("confirmed_at") ZonedDateTime confirmedAt,
    @JsonProperty("last_sign_in_at") ZonedDateTime lastSignInAt,
    @JsonProperty("app_metadata") Map<String, Object> appMetadata,
    @JsonProperty("user_metadata") Map<String, Object> userMetadata,
    List<Identity> identities,
    @JsonProperty("created_at") ZonedDateTime createdAt,
    @JsonProperty("updated_at") ZonedDateTime updatedAt,
    @JsonProperty("is_anonymous") boolean isAnonymous
) {
    public record Identity(
        @JsonProperty("identity_id") String identityId,
        String id,
        @JsonProperty("user_id") String userId,
        @JsonProperty("identity_data") Map<String, Object> identityData,
        String provider,
        @JsonProperty("last_sign_in_at") ZonedDateTime lastSignInAt,
        @JsonProperty("created_at") ZonedDateTime createdAt,
        @JsonProperty("updated_at") ZonedDateTime updatedAt,
        String email
    ) {}
}