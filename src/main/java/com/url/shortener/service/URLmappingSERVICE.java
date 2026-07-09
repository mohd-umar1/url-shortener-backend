package com.url.shortener.service;

import com.url.shortener.dto.URLmappingDTO;
import com.url.shortener.models.UrlMapping;
import com.url.shortener.models.User;
import com.url.shortener.repo.URLrepo;
import com.url.shortener.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@AllArgsConstructor
public class URLmappingSERVICE {


    public URLrepo urlrepo;

    public  UrlMapping getOriginalURL(String url) {
        UrlMapping urlmapping = urlrepo.findByShortenedUrl(url);
        return urlmapping;
    }

    public URLmappingDTO createshorturl(String originalURL, User user) {
        String shortUrl = generateShortURL(originalURL);
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(originalURL);
        urlMapping.setShortenedUrl(shortUrl);
        urlMapping.setCreation_date(LocalDateTime.now());
        urlMapping.setUser(user);
        urlrepo.save(urlMapping);
        return URLmappinng_dto_Object_create(urlMapping);

    }

    public URLmappingDTO URLmappinng_dto_Object_create(UrlMapping urlMapping) {
        URLmappingDTO urlmappingDTO = new URLmappingDTO();
        urlmappingDTO.setUsername(urlMapping.getUser().getUsername());
        urlmappingDTO.setId(urlMapping.getId());
        urlmappingDTO.setClick_count(urlMapping.getClick_count());
        urlmappingDTO.setOriginalURL(urlMapping.getOriginalUrl());
        urlmappingDTO.setShortURL(urlMapping.getShortenedUrl());
        urlmappingDTO.setCreateDate(urlMapping.getCreation_date());
        return urlmappingDTO;
    }

    public static String generateShortURL(String OriginalURL) {
        String character ="AaB2bCcDdE3eFfGgH2hIwiJjKkL3l6MmNnOoPp2QqRrSs34TtUuVvWwXxYyZz0123456789";
        StringBuilder builder = new StringBuilder(8);
        Random random = new Random();
        for(int i=0;i<8;i++){
            builder.append(character.charAt(random.nextInt(character.length())));
        }
       return builder.toString();
    }

}
