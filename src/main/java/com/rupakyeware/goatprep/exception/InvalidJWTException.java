package com.rupakyeware.goatprep.exception;

public class InvalidJWTException extends RuntimeException{
    public InvalidJWTException(String message) {
        super(message);
    }
}
