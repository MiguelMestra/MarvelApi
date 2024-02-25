package com.example.marvel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@NoArgsConstructor
public class Character {
    int id;
    String name;
    String resourceURI;
    Image thumbnail;
    Resources<SummaryMarvelEntity> comics;
    Resources<SummaryMarvelEntity> series;
    Resources<SummaryMarvelEntity> stories;

}
