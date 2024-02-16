package com.example.marvel.dto;

@lombok.Data
public class PaginatedResponse {
    private int offset;
    private int limit;
    private int count;
    private int total;
    private DataResponse data;
}
