package com.example.marvel.dto;

import lombok.Data;

import java.util.List;

@Data
public class CharacterDataResponse {
    private int offset;
    private int limit;
    private int count;
    private int total;
    private List<Character> results;

}
