package com.group2.detecthief.domain.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id) {
        super("Usuario con ID " + id + " no encontrado");
    }
}