package com.example.banking;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * NotFoundException
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    
}