package com.example.marvel.controller;

import com.example.marvel.dto.PaginatedResponse;
import com.example.marvel.service.MarvelApiService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
public class CharacterController {
    MarvelApiService marvelApiService;

    public CharacterController(MarvelApiService marvelApiService) {
        this.marvelApiService = marvelApiService;
    }

    @GetMapping("/character")
    @ResponseStatus(HttpStatus.OK)
    public PaginatedResponse getCharacter(@RequestParam(required = false) String name,
                                          @RequestParam (required = false) String series,
                                          @RequestParam (required = false) String stories) throws URISyntaxException {
        return marvelApiService.getAllCharacters(name,series,stories);
    }
}
