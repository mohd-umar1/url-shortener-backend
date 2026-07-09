package com.url.shortener.controller;

import com.url.shortener.models.UrlMapping;
import com.url.shortener.service.URLmappingSERVICE;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class redirectcontroller {

    private URLmappingSERVICE urlmappingservice;

    public ResponseEntity<Void> redirect(@PathVariable String url) {
        UrlMapping urlMapping = urlmappingservice.getOriginalURL(url);
           if(urlMapping == null) {
               HttpHeaders headers = new HttpHeaders();
               headers.add("Location",urlMapping.getOriginalUrl());
               return ResponseEntity.status(302).headers(headers).build();
           }
           else{
               return ResponseEntity.notFound().build();
           }
    }
}
