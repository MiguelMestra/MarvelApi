package com.example.marvel.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.web.ErrorResponse;

@NoArgsConstructor
public class DoesntExistException extends Exception{
    public final String ERROR = "No entity found with the required attributes";

    public DoesntExistException(Throwable cause) {
        super(cause);
    }
}
