package com.bib.hrassistantapp.advice;

public class CandidateStatusNotFoundErrorException extends RuntimeException {
    public CandidateStatusNotFoundErrorException(String errorMessage) {
        super(errorMessage);
    }
}