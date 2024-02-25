package com.example.marvel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DataResponse<T> {
    private int offset;
    private int limit;
    private int count;
    private int total;
    private List<T> results;

    public List<T> getResults() {
        return results;
    }
}
