package com.amr.project.exception;

public class InvalidDiscountException extends RuntimeException{
    private final ErrorMessage errorMessage;

    public InvalidDiscountException(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
