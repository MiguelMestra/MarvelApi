package com.example.marvel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.lang.reflect.Array;

@Data
@NoArgsConstructor
public class Comic extends SummaryMarvelEntity{
    int id;
    String description;
    String isbn;
    int pageCount;
    Image thumbnail;
    Resources<SummaryMarvelEntity> characters;
}
