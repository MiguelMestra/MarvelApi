package com.example.marvel.controller;

import com.example.marvel.dto.Character;
import com.example.marvel.service.MarvelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;

@RestController
public class CharacterController {
    MarvelService marvelService;

    public CharacterController(MarvelService marvelService) {
        this.marvelService = marvelService;
    }

    @GetMapping("/characters")
    @ResponseStatus(HttpStatus.OK)
    public List<Character> getCharacters(@RequestParam(required = false) String name,
                                         @RequestParam(required = false) List<Integer> series,
                                         @RequestParam(required = false) List<Integer> stories) throws URISyntaxException {
        return marvelService.getAllCharacters(name, series, stories);
    }
}
