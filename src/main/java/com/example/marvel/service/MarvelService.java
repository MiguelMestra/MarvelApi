package com.example.marvel.service;

import com.example.marvel.dto.Character;
import com.example.marvel.dto.*;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
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
    private GenericApiConexionService genericApiConexionService;

    public MarvelService(MarvelApiConexionService marvelApiConexionService, GenericApiConexionService genericApiConexionService) {
        this.marvelApiConexionService = marvelApiConexionService;
        this.genericApiConexionService = genericApiConexionService;
    }

    public List<Character> getAllCharacters(String name, List<Integer> series, List<Integer> stories) throws URISyntaxException {
        ArrayList<NameValuePair> filterParameters = new ArrayList<>();

        if (Objects.nonNull(name)) {
            filterParameters.add(new BasicNameValuePair("name", name));
        }
        if (Objects.nonNull(series)) {
            String seriesParameter = series.toString().substring(1, series.toString().length() - 1);
            filterParameters.add(new BasicNameValuePair("series", seriesParameter));
        }
        if (Objects.nonNull(stories)) {
            String storiesParameter = stories.toString().substring(1, stories.toString().length() - 1);
            filterParameters.add(new BasicNameValuePair("stories", storiesParameter));
        }

        PaginatedResponse<Character> responseCharacter = marvelApiConexionService.getAllCharacters(filterParameters);
        return responseCharacter.getData().getResults();
    }

    public List<Comic> getAllComics(List<Integer> characters) throws URISyntaxException {
        ArrayList<NameValuePair> filterParameters = new ArrayList<>();

        if (Objects.nonNull(characters)) {
            String characterParameter = characters.toString().substring(1, characters.toString().length() - 1);
            filterParameters.add(new BasicNameValuePair("characters", characterParameter));
        }

        PaginatedResponse<Comic> responseComics = marvelApiConexionService.getAllComics(filterParameters);
        return responseComics.getData().getResults();
    }

    public Comic getComicById(int id) throws URISyntaxException {

        PaginatedResponse<Comic> responseComic = marvelApiConexionService.getComicById(id);
        List<Comic> response = responseComic.getData().getResults();
        if (response.isEmpty()) {
            return null;
        }
        return response.get(0);
    }

    public CharacterBasicInformationResponseDto getCharacterById(int id) throws URISyntaxException, IOException {
        PaginatedResponse<Character> responseCharacter = marvelApiConexionService.getCharacterById(id);
        List<Character> response = responseCharacter.getData().getResults();
        if (response.isEmpty()) {
            return null;
        }
        Character character = response.get(0);
        ImageInformation characterImageInformation = character.getThumbnail();
        if (Objects.isNull(characterImageInformation)) {
            return null;
        }
        String imageUrl = characterImageInformation.getPath() + "." + characterImageInformation.getExtension();
        return new CharacterBasicInformationResponseDto(character.getName(), character.getDescription(), imageUrl);
    }
}
