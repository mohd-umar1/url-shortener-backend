package com.url.shortener.controller;

import com.url.shortener.dto.RegisterRequest;
import com.url.shortener.dto.loginRequest;
import com.url.shortener.models.User;
import com.url.shortener.service.URLmappingSERVICE;
import com.url.shortener.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
public class authcontroller {


    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody loginRequest loginRequest) {
        return ResponseEntity.ok(userService.authenticateuser(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<?> Registeruser(@RequestBody RegisterRequest registerRequest) {
        User user = new User();
        user.setName(registerRequest.getName());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setEmail(registerRequest.getEmail());
        user.setCreation_date(LocalDateTime.now());
        user.setRole("ROLE_USER");

        userService.registeruser(user);

        return ResponseEntity.ok("User registered successfully");
    }
}
