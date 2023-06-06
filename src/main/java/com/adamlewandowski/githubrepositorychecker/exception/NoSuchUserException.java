package com.adamlewandowski.githubrepositorychecker.exception;

public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException(String message) {
        super(message);
    }
}
