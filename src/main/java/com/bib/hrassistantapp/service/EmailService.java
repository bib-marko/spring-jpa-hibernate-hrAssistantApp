package com.bib.hrassistantapp.service;

import com.bib.hrassistantapp.model.EmailDetails;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletResponse;

public interface EmailService {

    ResponseEntity<String> sendSimpleMail(EmailDetails details);
    ResponseEntity<String> sendSimpleMailWithCC(EmailDetails details);
    ResponseEntity<String> sendSimpleMailWithBCC(EmailDetails details);
    ResponseEntity<String> sendMailWithAttachment(EmailDetails details);
    ResponseEntity<String> sendMailWithHTML(String position, String status, String subject, String template, String followUpDate, String upDate);
    ResponseEntity<String> exportEmailReport(HttpServletResponse response, Long id);
}
