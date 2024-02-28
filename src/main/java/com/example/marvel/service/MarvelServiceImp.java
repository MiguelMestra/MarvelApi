package com.example.marvel.service;

import com.example.marvel.dto.Character;
import com.example.marvel.dto.*;
import com.example.marvel.exceptions.DoesntExistException;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MarvelServiceImp implements IMarvelservice {

    private final MarvelApiConexionService marvelApiConexionService;

    public MarvelServiceImp(MarvelApiConexionService marvelApiConexionService) {
        this.marvelApiConexionService = marvelApiConexionService;
    }

    public ResponseEntity<List<Character>> getAllCharacters(String name, List<Integer> series, List<Integer> stories) throws URISyntaxException, DoesntExistException {
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

        if(responseCharacter.getData().getResults().isEmpty()){
            throw new DoesntExistException();
        }

        return new ResponseEntity<>(responseCharacter.getData().getResults(), HttpStatus.OK);
    }

    public ResponseEntity<List<Comic>> getAllComics(List<Integer> characters) throws DoesntExistException, URISyntaxException {
        ArrayList<NameValuePair> filterParameters = new ArrayList<>();

        if (Objects.nonNull(characters)) {
            String characterParameter = characters.toString().substring(1, characters.toString().length() - 1);
            filterParameters.add(new BasicNameValuePair("characters", characterParameter));
        }

        PaginatedResponse<Comic> responseComics = marvelApiConexionService.getAllComics(filterParameters);

        if(responseComics.getData().getResults().isEmpty()){
            throw new DoesntExistException();
        }

        return new ResponseEntity<>(responseComics.getData().getResults(), HttpStatus.OK);
    }

    public ResponseEntity<Comic> getComicById(int id) throws Exception {

        PaginatedResponse<Comic> responseComic = marvelApiConexionService.getComicById(id);
        List<Comic> response = responseComic.getData().getResults();
        if (response.isEmpty()) {
            throw new DoesntExistException();
        }
        return new ResponseEntity<>(response.get(0), HttpStatus.OK);
    }

    public ResponseEntity<CharacterBasicInformationResponseDto> getCharacterById(int id) throws Exception {

        PaginatedResponse<Character> responseCharacter = marvelApiConexionService.getCharacterById(id);
        List<Character> response = responseCharacter.getData().getResults();
        if (response.isEmpty()) {
            throw new DoesntExistException();
        }
        CharacterBasicInformationResponseDto informationResponse = getCharacterBasicInformationResponseDto(response);
        return new ResponseEntity<>(informationResponse, HttpStatus.OK);
    }

    private static CharacterBasicInformationResponseDto getCharacterBasicInformationResponseDto(List<Character> response) throws DoesntExistException {
        Character character = response.get(0);
        ImageInformation characterImageInformation = character.getThumbnail();
        if (Objects.isNull(characterImageInformation)) {
            throw new DoesntExistException();
        }
        String imageUrl = characterImageInformation.getPath() + "." + characterImageInformation.getExtension();
        return new CharacterBasicInformationResponseDto(character.getName(), character.getDescription(), imageUrl);
    }
}
