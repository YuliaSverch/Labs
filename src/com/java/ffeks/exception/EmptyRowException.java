package com.java.ffeks.exception;

public class EmptyRowException extends RuntimeException {

    public EmptyRowException() {
    }

    public EmptyRowException(String message) {
        super(message);
    }
}
