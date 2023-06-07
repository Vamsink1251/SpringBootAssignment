package com.springboot.assignment.exception;

public class ListEmptyException extends RuntimeException{
    public ListEmptyException(String message){
        super(message);
    }
}
