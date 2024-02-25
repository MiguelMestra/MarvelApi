package com.example.marvel.service;

import com.example.marvel.dto.Character;
import com.example.marvel.dto.Comic;
import com.example.marvel.dto.PaginatedResponse;
import com.example.marvel.utils.HashUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Service
public class MarvelApiConexionService {
    private final String BASE_URL = "http://gateway.marvel.com";
    private final String ALL_CHARACTER_PATH = "/v1/public/characters";
    private final String ALL_COMICS_PATH = "/v1/public/comics";

    @Value("${marvel.private_key}")
    private String privateKey;
    @Value("${marvel.public_key}")
    private String publicKey;

    private final RestTemplate restTemplate;

    public MarvelApiConexionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PaginatedResponse<Character> getAllCharacters(ArrayList<NameValuePair> aditionalParameters) throws URISyntaxException {

        URI uri = getUri(ALL_CHARACTER_PATH, aditionalParameters);
        return restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<PaginatedResponse<Character>>() {
        }).getBody();
    }

    public PaginatedResponse<Comic> getAllComics(ArrayList<NameValuePair> filterParameters) throws URISyntaxException {
        URI uri = getUri(ALL_COMICS_PATH, filterParameters);
        return restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<PaginatedResponse<Comic>>() {
        }).getBody();

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
