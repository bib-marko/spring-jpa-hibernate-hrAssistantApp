package com.bib.hrassistantapp.advice;

public class InvalidExcelException extends RuntimeException{
    public InvalidExcelException(String message) {
        super(message);
    }
}
