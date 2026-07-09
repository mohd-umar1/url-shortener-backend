package com.url.shortener.controller;

import com.url.shortener.dto.URLmappingDTO;
import com.url.shortener.models.User;
import com.url.shortener.service.URLmappingSERVICE;
import com.url.shortener.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("api/url")
public class generalController {

    private final UserService userService;

    private URLmappingSERVICE urlmappingservice;

    @PostMapping("/shorten")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<URLmappingDTO> shorten_url(@RequestBody Map<String,String> Request, Principal principal) {
        String OriginalURL = Request.get("originalURL");
        User user = userService.getuserbyusername(principal.getName());
        URLmappingDTO URLmappingDTO = urlmappingservice.createshorturl(OriginalURL,user);
        return ResponseEntity.ok(URLmappingDTO);
    }
}
