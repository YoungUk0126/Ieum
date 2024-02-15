package com.ukcorp.ieum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ExpiredTokenException extends RuntimeException{

    public ExpiredTokenException(String message) {
        super(message);
    }

}
