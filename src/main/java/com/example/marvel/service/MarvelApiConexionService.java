package com.example.marvel.service;

import com.example.marvel.dto.PaginatedCharacterResponse;
import com.example.marvel.utils.HashUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Service
public class MarvelApiConexionService {
    private final String BASE_URL = "http://gateway.marvel.com";
    private final String ALL_CHARACTER_PATH = "/v1/public/characters";

    @Value("${marvel.private_key}")
    private String privateKey;
    @Value("${marvel.public_key}")
    private String publicKey;

    private final RestTemplate restTemplate;

    public MarvelApiConexionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PaginatedCharacterResponse getAllCharacters(ArrayList<NameValuePair> aditionalParameters) throws URISyntaxException {

        URI uri = getUri(ALL_CHARACTER_PATH, aditionalParameters);
        return restTemplate.getForEntity(uri, PaginatedCharacterResponse.class).getBody();
    }

    private URI getUri(String path, ArrayList<NameValuePair> aditionalParameters) throws URISyntaxException {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String hash = HashUtil.getMD5(timestamp + privateKey + publicKey);
        return new URIBuilder(BASE_URL + path)
                .addParameter("ts", timestamp)
                .addParameter("apikey", publicKey)
                .addParameter("hash", hash)
                .addParameters(aditionalParameters)
                .build();
    }

}
