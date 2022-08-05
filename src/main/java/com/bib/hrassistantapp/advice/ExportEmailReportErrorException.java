package com.bib.hrassistantapp.advice;

public class ExportEmailReportErrorException extends RuntimeException{
    public ExportEmailReportErrorException(String errorMessage) {
        super(errorMessage);
    }
}
