package com.example.marvel.service;

import com.example.marvel.dto.Character;
import com.example.marvel.dto.CharacterBasicInformationResponseDto;
import com.example.marvel.dto.Comic;
import com.example.marvel.exceptions.DoesntExistException;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.List;

public interface IMarvelservice {
    ResponseEntity<List<Character>> getAllCharacters(String name, List<Integer> series, List<Integer> stories) throws URISyntaxException, DoesntExistException;
    ResponseEntity<List<Comic>> getAllComics(List<Integer> characters) throws URISyntaxException, DoesntExistException;
    ResponseEntity<Comic> getComicById(int id) throws URISyntaxException, DoesntExistException;
    ResponseEntity<CharacterBasicInformationResponseDto>  getCharacterById(int id) throws Exception;
}
