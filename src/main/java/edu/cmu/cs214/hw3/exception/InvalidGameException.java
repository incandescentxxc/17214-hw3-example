package edu.cmu.cs214.hw3.exception;

public class InvalidGameException extends Exception{
    private String message;

    public InvalidGameException(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
}
