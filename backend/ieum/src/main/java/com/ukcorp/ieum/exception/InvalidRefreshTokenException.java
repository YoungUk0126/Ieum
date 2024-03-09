package com.ukcorp.ieum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class InvalidRefreshTokenException extends RuntimeException{

    public InvalidRefreshTokenException(String message) {
        super(message);
    }

}
