package com.bridgeLabz.bookStore.exception;

public class BookNotFoundByIdException extends RuntimeException {

    private String message;

    public BookNotFoundByIdException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
