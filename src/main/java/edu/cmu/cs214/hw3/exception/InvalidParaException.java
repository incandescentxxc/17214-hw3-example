package edu.cmu.cs214.hw3.exception;

public class InvalidParaException extends Exception{
    private String message;

    public InvalidParaException(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
}
