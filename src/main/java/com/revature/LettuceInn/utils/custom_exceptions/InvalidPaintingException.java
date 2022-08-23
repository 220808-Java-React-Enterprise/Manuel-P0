package com.revature.LettuceInn.utils.custom_exceptions;

public class InvalidPaintingException extends RuntimeException {
    public InvalidPaintingException() {
    }

    public InvalidPaintingException(String message) {
        super(message);
    }

    public InvalidPaintingException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPaintingException(Throwable cause) {
        super(cause);
    }

    public InvalidPaintingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
