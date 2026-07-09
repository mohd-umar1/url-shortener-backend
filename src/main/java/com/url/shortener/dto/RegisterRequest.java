package com.url.shortener.dto;

import lombok.Data;

import java.util.Set;

@Data
public class RegisterRequest {
    private String name;
    private String username;
    private String password;
    private Set<String> roles;
    private String email;
}
