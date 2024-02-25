package com.example.marvel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.lang.reflect.Array;

@Data
@NoArgsConstructor
public class Comic {
    int id;
    String title;
    String resourceURI;
    String description;
    String isbn;
    int pageCount;
    Image thumbnail;
    Resources<SummaryMarvelEntity> characters;
    Resources<SummaryMarvelEntity> series;
    Resources<SummaryMarvelEntity> stories;
}
