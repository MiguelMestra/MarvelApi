package com.example.marvel.dto;

import lombok.Data;

import java.util.List;

@Data
public class Resources <T>{
    int available;
    int returned;
    String collectionURI;
    List<T> items;

}
