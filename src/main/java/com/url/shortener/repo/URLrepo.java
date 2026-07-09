package com.url.shortener.repo;

import com.url.shortener.models.UrlMapping;
import com.url.shortener.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface URLrepo extends JpaRepository<UrlMapping,Long> {
    UrlMapping findByShortenedUrl(String shortUrl);
    List<UrlMapping> findByUser(User user);
}
