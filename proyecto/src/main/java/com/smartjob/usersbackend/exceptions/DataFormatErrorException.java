package com.smartjob.usersbackend.exceptions;

public class DataFormatErrorException extends RuntimeException{
    public DataFormatErrorException(String message) {
        super(message);
    }
}
