package com.example.marvel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaginatedCharacterResponse {
    int code;
    String status;
    private CharacterDataResponse data;
}
