package com.java.ffeks.exception;

public class InvalidIpException extends RuntimeException {

    public InvalidIpException() {
    }

    public InvalidIpException(String message) {
        super(message);
    }
}
