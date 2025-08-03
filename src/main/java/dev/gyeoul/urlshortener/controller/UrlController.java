package dev.gyeoul.urlshortener.controller;

import dev.gyeoul.urlshortener.service.KeyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
public class UrlController {

    private final KeyService keyService;

    public UrlController(KeyService keyService) {
        this.keyService = keyService;
    }

    @GetMapping("/")
    public String generateId() {
        return keyService.generateNanoId();
    }

    @PostMapping("/gen")
    public ResponseEntity<String> generateShortenUrl(){
        return ResponseEntity.created(URI.create("")).build();
    }
}
