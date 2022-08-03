package com.bib.hrassistantapp.advice;

public class TemplateNotExistingErrorException  extends RuntimeException{
    public TemplateNotExistingErrorException(String errorMessage) {
        super(errorMessage);
    }
}

