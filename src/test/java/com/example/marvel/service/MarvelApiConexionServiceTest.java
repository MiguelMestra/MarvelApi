package com.example.marvel.service;

import com.example.marvel.dto.Character;
import com.example.marvel.dto.Comic;
import com.example.marvel.dto.PaginatedResponse;
import com.example.marvel.exceptions.DoesntExistException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MarvelApiConexionServiceTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    MarvelApiConexionService conexionService;

    @Test
    void getAllCharactersSuccessfully() throws URISyntaxException {

        PaginatedResponse<Character> mockResponse = PaginatedResponse.<Character>builder().code(200).build();

        ResponseEntity<PaginatedResponse<Character>> mockResponseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);
        when(restTemplate.exchange(any(), eq(HttpMethod.GET), eq(null), eq(new ParameterizedTypeReference<PaginatedResponse<Character>>() {
        })))
                .thenReturn(mockResponseEntity);

        PaginatedResponse<Character> response = conexionService.getAllCharacters(new ArrayList<>());

        assertEquals(mockResponse, response);

    }

    @Test
    void getAllComicsSuccessfully() throws URISyntaxException {
        PaginatedResponse<Comic> mockResponse = PaginatedResponse.<Comic>builder().code(200).build();

        ResponseEntity<PaginatedResponse<Comic>> mockResponseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);
        when(restTemplate.exchange(any(), eq(HttpMethod.GET), eq(null), eq(new ParameterizedTypeReference<PaginatedResponse<Comic>>() {
        })))
                .thenReturn(mockResponseEntity);

        PaginatedResponse<Comic> response = conexionService.getAllComics(new ArrayList<>());

        assertEquals(mockResponse, response);
    }

    @Test
    void getComicByIdSuccessFully() throws Exception {
        ArgumentCaptor<URI> uriCaptor = ArgumentCaptor.forClass(URI.class);
        PaginatedResponse<Comic> mockResponse = PaginatedResponse.<Comic>builder().code(200).build();

        ResponseEntity<PaginatedResponse<Comic>> mockResponseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);
        when(restTemplate.exchange(uriCaptor.capture(), eq(HttpMethod.GET), eq(null), eq(new ParameterizedTypeReference<PaginatedResponse<Comic>>() {
        })))
                .thenReturn(mockResponseEntity);


        PaginatedResponse<Comic> response = conexionService.getComicById(1945);
        URI capturedUri = uriCaptor.getValue();

        assertEquals(mockResponse, response);
        assertTrue(capturedUri.getPath().contains("/1945"));
    }

    @Test
    void noComicReturned() throws Exception {
        when(restTemplate.exchange(any(), eq(HttpMethod.GET), eq(null), eq(new ParameterizedTypeReference<PaginatedResponse<Comic>>() {
        })))
                .thenThrow(new HttpClientErrorException(HttpStatus.valueOf(404)));


        assertThrows(DoesntExistException.class, () -> conexionService.getComicById(1945));
    }

    @Test
    void getCharacterByIdSuccessfully() throws Exception {
        ArgumentCaptor<URI> uriCaptor = ArgumentCaptor.forClass(URI.class);
        PaginatedResponse<Character> mockResponse = PaginatedResponse.<Character>builder().code(200).build();

        ResponseEntity<PaginatedResponse<Character>> mockResponseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);
        when(restTemplate.exchange(uriCaptor.capture(), eq(HttpMethod.GET), eq(null), eq(new ParameterizedTypeReference<PaginatedResponse<Character>>() {
        })))
                .thenReturn(mockResponseEntity);

        PaginatedResponse<Character> response = conexionService.getCharacterById(1945);
        URI capturedUri = uriCaptor.getValue();

        assertEquals(mockResponse, response);
        assertTrue(capturedUri.getPath().contains("/1945"));
    }

    @Test
    void noCharacterReturned() throws Exception {
        when(restTemplate.exchange(any(), eq(HttpMethod.GET), eq(null), eq(new ParameterizedTypeReference<PaginatedResponse<Character>>() {
        })))
                .thenThrow(new HttpClientErrorException(HttpStatus.valueOf(404)));


        assertThrows(DoesntExistException.class, () -> conexionService.getCharacterById(1945));
    }
}