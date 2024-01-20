package com.team.ourblog.common;

public class InvalidPasswordException extends RuntimeException{


    public InvalidPasswordException(String message){
        super(message);
    }
}
