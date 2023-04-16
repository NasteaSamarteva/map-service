package com.mapservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MapServiceException extends RuntimeException {
    private HttpStatus httpStatus;
    public MapServiceException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus=httpStatus;
    }
}
