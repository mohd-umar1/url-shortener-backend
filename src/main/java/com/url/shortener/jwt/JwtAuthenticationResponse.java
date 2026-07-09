package com.url.shortener.jwt;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtAuthenticationResponse {
            private String token;      // Response DTO(data transfer object)
                                       // Sent to client after successful login// Carries JWT token data (no business logic)
}
