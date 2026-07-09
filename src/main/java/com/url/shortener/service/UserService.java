package com.url.shortener.service;

import com.url.shortener.dto.loginRequest;
import com.url.shortener.jwt.JwtAuthenticationResponse;
import com.url.shortener.jwt.JwtUtils;
import com.url.shortener.models.User;
import com.url.shortener.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserService {
    public UserRepo userRepo;

    public PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    public JwtUtils jwtUtils;



    public void registeruser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public User getuserbyusername(String username) {
        User user = userRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("username not found"));
        return user;
    }

    public JwtAuthenticationResponse authenticateuser(loginRequest loginRequest) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        String jwttoken = jwtUtils.generate_jwt(userDetails);
        return new JwtAuthenticationResponse(jwttoken);
    }
    }

