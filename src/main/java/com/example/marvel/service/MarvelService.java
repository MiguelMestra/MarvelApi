package com.example.marvel.service;

import com.example.marvel.dto.PaginatedCharacterResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class MarvelService {
    private final String BASE_URL = "http://gateway.marvel.com";
    private final String ALL_CHARACTER_PATH = "/v1/public/characters";

    @Value("${marvel.private_key}")
    private String privateKey;
    @Value("${marvel.public_key}")
    private String publicKey;

    private MarvelApiConexionService marvelApiConexionService;

    public MarvelService(MarvelApiConexionService marvelApiConexionService) {
        this.marvelApiConexionService = marvelApiConexionService;
    }

    public PaginatedCharacterResponse getAllCharacters(String name, String series, String stories) throws URISyntaxException {
        ArrayList<NameValuePair> filterParameters = new ArrayList<>();
        if (Objects.nonNull(name)) {
            filterParameters.add(new BasicNameValuePair("name", name));
        }
        if (Objects.nonNull(series)) {
            filterParameters.add(new BasicNameValuePair("series", series));
        }
        if (Objects.nonNull(stories)) {
            filterParameters.add(new BasicNameValuePair("stories", stories));
        }


        return marvelApiConexionService.getAllCharacters(filterParameters);
    }

}
