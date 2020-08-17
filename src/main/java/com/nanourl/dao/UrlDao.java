package com.nanourl.dao;

import com.nanourl.pojo.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UrlDao extends JpaRepository<Url, Long> {
    public List<Url> findBylongUrlAndExpired(String longUrl, int expired);
}
