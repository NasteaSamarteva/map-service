package com.mapservice.exception.handling;

import com.mapservice.dto.ErrorResponseDto;
import com.mapservice.exception.MapServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(value
            = {MapServiceException.class})
    public ResponseEntity<ErrorResponseDto> handleConflict(
            MapServiceException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setErrorTime(LocalDateTime.now());
        errorResponseDto.setMessage(ex.getMessage());
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(errorResponseDto);
    }
}
