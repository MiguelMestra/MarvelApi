package com.example.marvel.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GenericApiConexionService {
    private final RestTemplate restTemplate;

    public GenericApiConexionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public byte[] getImageFromUrl(String imageUrl) {
        ResponseEntity<byte[]> response = restTemplate.getForEntity(imageUrl, byte[].class);
        return  response.getBody();
    }
}
