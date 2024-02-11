package com.example.marvel.dto;

import java.util.List;

public class Resources <T>{
    int available;
    int returned;
    String collectionURI;
    List<T> items;

}
