package com.bridgeLabz.bookStore.exception;

public class WishlistNotFoundByIdException extends RuntimeException {

    private String message;

    public WishlistNotFoundByIdException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
