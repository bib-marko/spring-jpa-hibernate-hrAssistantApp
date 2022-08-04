package com.bib.hrassistantapp.controller;

import com.bib.hrassistantapp.model.EmailDetails;
import com.bib.hrassistantapp.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendMail")
    public ResponseEntity<String> sendMail(@RequestBody EmailDetails details){
        return emailService.sendSimpleMail(details);
    }

    @PostMapping("/sendMailWithCC")
    public ResponseEntity<String> sendMailWithCC(@RequestBody EmailDetails details){
        return emailService.sendSimpleMailWithCC(details);
    }

    @PostMapping("/sendMailWithBCC")
    public ResponseEntity<String> sendMailWithBCC(@RequestBody EmailDetails details){
        return emailService.sendSimpleMailWithBCC(details);
    }

    @PostMapping("/sendMailWithAttachment")
    public ResponseEntity<String> sendMailWithAttachment(@RequestBody EmailDetails details){
        return emailService.sendMailWithAttachment(details);
    }

    @PostMapping("/send/{position}/{status}/{subject}/{template}/{followUpDate}")
    public ResponseEntity<String> sendHtmlEmail(@PathVariable(value = "position") String position,
                                                @PathVariable(value = "status") String status,
                                                @PathVariable(value = "subject") String subject,
                                                @PathVariable(value = "template")String template,
                                                @PathVariable(value = "followUpDate")String followUpDate){
        return emailService.sendMailWithHTML(position,status,subject,template,followUpDate);
    }


}