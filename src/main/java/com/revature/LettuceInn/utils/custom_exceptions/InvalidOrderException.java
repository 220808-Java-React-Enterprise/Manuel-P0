package com.revature.LettuceInn.utils.custom_exceptions;

public class InvalidOrderException extends RuntimeException {
    public InvalidOrderException() {
    }

    public InvalidOrderException(String message) {
        super(message);
    }

    public InvalidOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidOrderException(Throwable cause) {
        super(cause);
    }

    public InvalidOrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
