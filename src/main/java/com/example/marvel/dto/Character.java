package com.example.marvel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@NoArgsConstructor
public class Character {
    int id;
    String name;
    String description;
    Image thumbnail;
    Resources<Comic> comics;

}
