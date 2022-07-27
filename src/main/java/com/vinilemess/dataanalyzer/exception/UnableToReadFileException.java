package com.vinilemess.dataanalyzer.exception;

public class UnableToReadFileException extends RuntimeException{
    public UnableToReadFileException(String message) {
        super("Unable to read file : " + message);
    }
}
