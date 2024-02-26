package com.example.marvel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CharacterBasicInformationResponseDto {
    String name;
    String description;
    String image;
}
