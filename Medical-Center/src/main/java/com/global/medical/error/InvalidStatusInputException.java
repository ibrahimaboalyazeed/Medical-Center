package com.global.medical.error;

public class InvalidStatusInputException extends RuntimeException {
    public InvalidStatusInputException(String message) {
        super(message);
    }
}

