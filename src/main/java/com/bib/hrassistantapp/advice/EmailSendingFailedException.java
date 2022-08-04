package com.bib.hrassistantapp.advice;

public class EmailSendingFailedException extends RuntimeException {
    public EmailSendingFailedException() {
        super("Error occurs while sending mail!");
    }
}