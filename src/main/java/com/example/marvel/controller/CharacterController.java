package com.example.marvel.controller;

import com.example.marvel.dto.PaginatedCharacterResponse;
import com.example.marvel.service.MarvelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
public class CharacterController {
    MarvelService marvelService;

    public CharacterController(MarvelService marvelService) {
        this.marvelService = marvelService;
    }

    @GetMapping("/character")
    @ResponseStatus(HttpStatus.OK)
    public PaginatedCharacterResponse getCharacters(@RequestParam(required = false) String name,
                                                    @RequestParam (required = false) String series,
                                                    @RequestParam (required = false) String stories) throws URISyntaxException {
        return marvelService.getAllCharacters(name,series,stories);
    }
}
