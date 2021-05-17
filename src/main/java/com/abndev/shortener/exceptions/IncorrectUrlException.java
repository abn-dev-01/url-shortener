package com.abndev.shortener.exceptions;

public class IncorrectUrlException extends Exception {
    public IncorrectUrlException() {
        super();
    }

    public IncorrectUrlException(String message) {
        super(message);
    }
}
