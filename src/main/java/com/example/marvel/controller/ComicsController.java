package com.example.marvel.controller;

import com.example.marvel.dto.Comic;
import com.example.marvel.service.MarvelServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comics")
public class ComicsController {
    MarvelServiceImp marvelService;

    public ComicsController(MarvelServiceImp marvelService) {
        this.marvelService = marvelService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Comic>> getComics(@RequestParam(required = false) List<Integer> characters) throws Exception {
        return marvelService.getAllComics(characters);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Comic> getComicById(@PathVariable int id) throws Exception {
        return marvelService.getComicById(id);
    }


}
