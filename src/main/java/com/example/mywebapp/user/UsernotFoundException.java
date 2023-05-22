package com.example.mywebapp.user;

public class UsernotFoundException extends Throwable {

    public UsernotFoundException(String message) {
        super(message);
    }
}
