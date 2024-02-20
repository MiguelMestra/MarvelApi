package com.example.marvel.service;

import com.example.marvel.dto.PaginatedResponse;
import com.example.marvel.utils.HashUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class MarvelApiService {
    private final String BASE_URL="http://gateway.marvel.com";
    private final String ALL_CHARACTER_PATH="/v1/public/characters";

    @Value("${marvel.private_key}")
    private String privateKey;
    @Value("${marvel.public_key}")
    private String publicKey;
    private final RestTemplate restTemplate;

    public MarvelApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public PaginatedResponse getAllCharacters(String name, String series, String stories) throws URISyntaxException {
        ArrayList<NameValuePair> filterParameters = new ArrayList<>();
        if (Objects.nonNull(name)){
            filterParameters.add(new BasicNameValuePair("name",name));
        }
        if (Objects.nonNull(series)){
            filterParameters.add(new BasicNameValuePair("series",series));
        }
        if (Objects.nonNull(stories)){
            filterParameters.add(new BasicNameValuePair("stories",stories));
        }

        URI uri = getUri(ALL_CHARACTER_PATH,filterParameters);
        return restTemplate.getForEntity(uri, PaginatedResponse.class).getBody();
    }

    private URI getUri(String path, ArrayList<NameValuePair> aditionalParameters) throws URISyntaxException {
        String timestamp = String.valueOf(System.currentTimeMillis() );
        String hash = HashUtil.getMD5(timestamp+privateKey+publicKey);
        return new URIBuilder(BASE_URL+path)
                .addParameter("ts", timestamp)
                .addParameter("apikey",publicKey)
                .addParameter("hash", hash)
                .addParameters(aditionalParameters)
                .build();
    }
}
