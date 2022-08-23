package com.bib.hrassistantapp.service;

import com.bib.hrassistantapp.model.dto.BuildEmailDTO;
import org.springframework.http.ResponseEntity;

public interface EmailService {

    ResponseEntity<String> sendMail(BuildEmailDTO buildEmailDTO);
    ResponseEntity<String> exportEmailReport(Long id);
}
