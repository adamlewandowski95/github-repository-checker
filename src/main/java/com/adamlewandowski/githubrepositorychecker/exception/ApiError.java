package com.adamlewandowski.githubrepositorychecker.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public class ApiError {

    private final HttpStatusCode status;
    private final String message;

}
