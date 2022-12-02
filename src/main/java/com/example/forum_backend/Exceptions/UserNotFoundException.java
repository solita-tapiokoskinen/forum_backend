package com.example.forum_backend.Exceptions;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3;

    public UserNotFoundException(String message) {
        super(message);
    }
}
