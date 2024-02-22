package com.example.marvel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CharacterDataResponse {
    private int offset;
    private int limit;
    private int count;
    private int total;
    private List<Character> results;

    public List<Character> getResults() {
        return results;
    }
}
