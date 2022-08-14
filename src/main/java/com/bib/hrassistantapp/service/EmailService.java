package com.bib.hrassistantapp.service;

import com.bib.hrassistantapp.model.EmailDetails;
import com.bib.hrassistantapp.model.dto.BuildEmailDTO;
import org.springframework.http.ResponseEntity;

public interface EmailService {

    ResponseEntity<String> sendSimpleMail(EmailDetails details);
    ResponseEntity<String> sendMailWithHTML(BuildEmailDTO buildEmailDTO);
    ResponseEntity<String> exportEmailReport(Long id);
}
