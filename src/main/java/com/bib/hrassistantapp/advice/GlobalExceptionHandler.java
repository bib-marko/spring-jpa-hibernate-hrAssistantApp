package com.bib.hrassistantapp.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);

        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> exception(EntityNotFoundException exception){
        CustomErrorMessage error = new CustomErrorMessage();
        error.setMessage(exception.getMessage());
        error.setTimestamp(new Date());
        error.setStatus(String.valueOf(HttpStatus.NOT_FOUND.value()));
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(EntityAlreadyExistException.class)
    public ResponseEntity<Object> exception(EntityAlreadyExistException exception){
        CustomErrorMessage error = new CustomErrorMessage();
        error.setMessage(exception.getMessage());
        error.setTimestamp(new Date());
        error.setStatus(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()));
        return new ResponseEntity(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = InvalidExcelException.class)
    public ResponseEntity<Object> exception (InvalidExcelException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(TemplateTitleErrorException.class)
    public ResponseEntity<?> handleValidationExceptions(TemplateTitleErrorException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TemplateNotExistingErrorException.class)
    public ResponseEntity<?> handleValidationExceptions(TemplateNotExistingErrorException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmailSendingFailedException.class)
    public ResponseEntity<Object> exception (EmailSendingFailedException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = ExportEmailReportErrorException.class)
    public ResponseEntity<Object> exception (ExportEmailReportErrorException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}

