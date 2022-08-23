package com.bib.hrassistantapp.controller;

import com.bib.hrassistantapp.model.dto.BuildEmailDTO;
import com.bib.hrassistantapp.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendMail")
    public ResponseEntity<String> sendMail(@RequestBody BuildEmailDTO buildEmailDTO){
        return emailService.sendMail(buildEmailDTO);
    }

    @GetMapping("/send/export/{id}")
    public ResponseEntity<String> export(@PathVariable(value = "id") Long id) {
        return emailService.exportEmailReport(id);
    }

}