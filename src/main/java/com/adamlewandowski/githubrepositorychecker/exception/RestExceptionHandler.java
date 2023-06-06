package com.adamlewandowski.githubrepositorychecker.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnsuportedMediaTypeException.class)
    public ResponseEntity<Object> notFoundHandler(UnsuportedMediaTypeException exception) {
        ApiError apiError = new ApiError(NOT_ACCEPTABLE, exception.getMessage());
        return new ResponseEntity<>(apiError, NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NoSuchUserException.class)
    public ResponseEntity<Object> notFoundHandler(NoSuchUserException exception) {
        ApiError apiError = new ApiError(NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(apiError, NOT_FOUND);
    }

    @ExceptionHandler(UnexpectedStatusCodeException.class)
    public ResponseEntity<Object> notFoundHandler(UnexpectedStatusCodeException exception) {
        ApiError apiError = new ApiError(I_AM_A_TEAPOT, exception.getMessage());
        return new ResponseEntity<>(apiError, I_AM_A_TEAPOT);
    }
}