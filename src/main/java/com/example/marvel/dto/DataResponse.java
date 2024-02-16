package com.example.marvel.dto;

import java.util.List;

@lombok.Data
public class DataResponse {
    int code;
    String status;
    private List<Character> results;

}
