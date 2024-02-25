package com.example.marvel.controller;

import com.example.marvel.dto.Character;
import com.example.marvel.dto.Comic;
import com.example.marvel.service.MarvelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ComicsController {
    MarvelService marvelService;

    public ComicsController(MarvelService marvelService) {
        this.marvelService = marvelService;
    }

    @GetMapping("/comics")
    @ResponseStatus(HttpStatus.OK)
    public List<Comic> getComics(@RequestParam (required = false) List<Integer> characters) throws URISyntaxException {
        return marvelService.getAllComics(characters);
    }
}
