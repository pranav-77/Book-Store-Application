package com.bridgeLabz.bookStore.exception;

public class UserNotFoundByIdException extends RuntimeException {

    private final String message;

    public UserNotFoundByIdException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

