package com.adamlewandowski.githubrepositorychecker.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    ObjectMapper objectMapper;

    @ExceptionHandler(NoSuchUserException.class)
    public ResponseEntity<Object> noSuchUser(NoSuchUserException exception) {
        ApiError apiError = new ApiError(NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(apiError, NOT_FOUND);
    }

    @ExceptionHandler(UnexpectedStatusCodeException.class)
    public ResponseEntity<Object> unexpectedStatusCode(UnexpectedStatusCodeException exception) {
        ApiError apiError = new ApiError(I_AM_A_TEAPOT, exception.getMessage());
        return new ResponseEntity<>(apiError, I_AM_A_TEAPOT);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<Object> handleHttpMediaTypeNotAcceptable() throws JsonProcessingException {
        ApiError apiError = new ApiError(NOT_ACCEPTABLE, "Response can be generated only in json format");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(objectMapper.writeValueAsString(apiError), responseHeaders, NOT_ACCEPTABLE);
    }

}