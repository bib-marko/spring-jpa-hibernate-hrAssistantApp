package com.bib.hrassistantapp.advice;

public class TemplateTitleErrorException extends RuntimeException{
    public TemplateTitleErrorException(String errorMessage) {
        super(errorMessage);
    }
}
