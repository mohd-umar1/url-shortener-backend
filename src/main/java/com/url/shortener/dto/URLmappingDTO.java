package com.url.shortener.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class URLmappingDTO {

    private Long id;
    private String originalURL;
    private String shortURL;
    private LocalDateTime createDate;
    private String username;
    private Long click_count;
}
