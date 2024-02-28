package com.example.marvel.controller;

import com.example.marvel.dto.Character;
import com.example.marvel.dto.CharacterBasicInformationResponseDto;
import com.example.marvel.service.MarvelServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    MarvelServiceImp marvelService;

    public CharacterController(MarvelServiceImp marvelService) {
        this.marvelService = marvelService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Character>> getCharacters(@RequestParam(required = false) String name,
                                                         @RequestParam(required = false) List<Integer> series,
                                                         @RequestParam(required = false) List<Integer> stories) throws Exception {
        return marvelService.getAllCharacters(name, series, stories);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CharacterBasicInformationResponseDto> getSummaryCharacterById(@PathVariable int id) throws Exception {
        return marvelService.getCharacterById(id);
    }

}
