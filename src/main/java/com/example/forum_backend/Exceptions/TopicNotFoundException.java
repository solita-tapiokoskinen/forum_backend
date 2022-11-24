package com.example.forum_backend.Exceptions;

public class TopicNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public TopicNotFoundException(String message) {
        super(message);
    }

}
