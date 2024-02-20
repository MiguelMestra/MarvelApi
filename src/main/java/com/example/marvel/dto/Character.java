package com.example.marvel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@NoArgsConstructor
public class Character extends SummaryMarvelEntity{
    int id;
    String description;
    Image thumbnail;
    Resources<SummaryMarvelEntity> comics;

}
