package com.example.marvel.controller;

import com.example.marvel.dto.PaginatedResponse;
import com.example.marvel.service.MarvelApiService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
public class CharacterController {
    MarvelApiService marvelApiService;

    public CharacterController(MarvelApiService marvelApiService) {
        this.marvelApiService = marvelApiService;
    }

    @GetMapping("/character")
    @ResponseStatus(HttpStatus.OK)
    public PaginatedResponse getCharacter(@PathVariable (required = false) String name,
                                          @PathVariable (required = false) String series,
                                          @PathVariable (required = false) String stories) throws URISyntaxException {
        return marvelApiService.getAllCharacters(name,series,stories);
    }
}
