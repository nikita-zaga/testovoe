package com.example.demo.exception;

public class LordNotFoundException extends Exception {
    public LordNotFoundException(Long id) {
        super("Lord by '" + id + "' not found.");
    }
}
