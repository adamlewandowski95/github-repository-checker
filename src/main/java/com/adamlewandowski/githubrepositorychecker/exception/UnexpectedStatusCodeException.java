package com.adamlewandowski.githubrepositorychecker.exception;

public class UnexpectedStatusCodeException extends RuntimeException {
    public UnexpectedStatusCodeException(String message) {
        super(message);
    }
}
