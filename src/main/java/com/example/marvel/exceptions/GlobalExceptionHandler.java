package com.example.marvel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.URISyntaxException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({DoesntExistException.class})
    public ResponseEntity<ErrorResponseDto> noResult(DoesntExistException ex) {
        String messageException = "No entity found with the required attributes";
        ErrorResponseDto errorResponse = new ErrorResponseDto(messageException, ex.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler({URISyntaxException.class})
    public ResponseEntity<ErrorResponseDto> failedUriInstance(URISyntaxException ex) {
        String messageException = "Error creating Uri instance for connection to external api";
        ErrorResponseDto errorResponse = new ErrorResponseDto(messageException, ex.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }



    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorResponseDto> typleMismatchPetition(MethodArgumentTypeMismatchException ex) {
        String messageException = "one or more attributes sent are of the wrong type, please read endpoint documentation";
        ErrorResponseDto errorResponse = new ErrorResponseDto(messageException, ex.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
