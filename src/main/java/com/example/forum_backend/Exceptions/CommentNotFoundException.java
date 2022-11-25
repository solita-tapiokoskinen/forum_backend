package com.example.forum_backend.Exceptions;

public class CommentNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 2;

    public CommentNotFoundException(String message) {
        super(message);
    }

}
