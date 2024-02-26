package com.example.marvel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Character {
    int id;
    String name;
    String resourceURI;
    String description;
    ImageInformation thumbnail;
    Resources<SummaryMarvelEntity> comics;
    Resources<SummaryMarvelEntity> series;
    Resources<SummaryMarvelEntity> stories;

}
