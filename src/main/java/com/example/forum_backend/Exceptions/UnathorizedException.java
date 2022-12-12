package com.example.forum_backend.Exceptions;

public class UnathorizedException extends RuntimeException {

    private static final long serialVersionUID = 4;

    public UnathorizedException(String message) {
        super(message);
    }
}
