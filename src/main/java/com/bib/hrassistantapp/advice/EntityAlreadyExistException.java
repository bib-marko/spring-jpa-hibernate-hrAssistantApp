package com.bib.hrassistantapp.advice;

public class EntityAlreadyExistException extends RuntimeException{
    public EntityAlreadyExistException(String message) {
        super(message);
    }
}
