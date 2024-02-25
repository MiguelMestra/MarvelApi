package com.example.marvel.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
public class PaginatedResponse<T> {
    int code;
    String status;
    private DataResponse<T> data;

    public DataResponse<T> getData() {
        return data;
    }
}
