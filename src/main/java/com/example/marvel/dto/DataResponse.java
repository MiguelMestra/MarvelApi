package com.example.marvel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse<T> {
    private int offset;
    private int limit;
    private int count;
    private int total;
    private List<T> results;

}
