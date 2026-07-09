package com.url.shortener.models;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ClickEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime click_date;

    @ManyToOne
    @JoinColumn(name="clicked_url")
    private UrlMapping mapped_url;
}
