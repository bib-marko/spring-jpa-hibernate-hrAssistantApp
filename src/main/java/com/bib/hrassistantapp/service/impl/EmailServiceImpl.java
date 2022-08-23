package com.bib.hrassistantapp.service.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import com.bib.hrassistantapp.advice.*;
import com.bib.hrassistantapp.model.Candidate;
import com.bib.hrassistantapp.model.dto.BuildEmailDTO;
import com.bib.hrassistantapp.repository.CandidateRepository;
import com.bib.hrassistantapp.repository.EmailReportRepository;
import com.bib.hrassistantapp.repository.TemplateRepository;
import com.bib.hrassistantapp.service.EmailService;
import com.bib.hrassistantapp.utils.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String sender;

    private final JavaMailSender javaMailSender;

    private final TemplateRepository templateRepository;

    private final CandidateRepository candidateRepository;

    private final EmailReportRepository emailReportRepository;


    public EmailServiceImpl(JavaMailSender javaMailSender, TemplateRepository templateRepository, CandidateRepository candidateRepository, EmailReportRepository emailReportRepository) {
        this.javaMailSender = javaMailSender;
        this.templateRepository = templateRepository;
        this.candidateRepository = candidateRepository;
        this.emailReportRepository = emailReportRepository;
    }

    public ResponseEntity<String> sendMail(BuildEmailDTO buildEmailDTO) {


        SendingEmailUtil sendingEmailUtil = new SendingEmailUtil(javaMailSender);

        List<String> list = new ArrayList<>();

        if (buildEmailDTO.getRecipientTO() != null) {
            sendingEmailUtil.setRecipientTO(EmailUtility.formatEmailsToArray(buildEmailDTO.getRecipientTO()));
            list.addAll(Arrays.asList(buildEmailDTO.getRecipientTO()));
        }
        if (buildEmailDTO.getRecipientCC() != null) {
            sendingEmailUtil.setRecipientCC(EmailUtility.formatEmailsToArray(buildEmailDTO.getRecipientCC()));
            list.addAll(Arrays.asList(buildEmailDTO.getRecipientCC()));
        }
        if (buildEmailDTO.getRecipientBCC() != null) {
            sendingEmailUtil.setRecipientBCC(EmailUtility.formatEmailsToArray(buildEmailDTO.getRecipientBCC()));
            list.addAll(Arrays.asList(buildEmailDTO.getRecipientCC()));
        }
        if (buildEmailDTO.getAttachment() != null) {
            sendingEmailUtil.setHaveAttachment(buildEmailDTO.getAttachment());
        }

        if(buildEmailDTO.getTemplate() != null){
            buildEmailDTO.setPosition(EmailUtility.validateJobPosition(buildEmailDTO.getPosition()));
            List<Candidate> list1 = candidateRepository.findAll("", buildEmailDTO.getPosition(), buildEmailDTO.getStatus());
            try {
                sendingEmailUtil.setRecipientTO(EmailUtility.formatEmails(candidateRepository.findAll("", buildEmailDTO.getPosition(), buildEmailDTO.getStatus())));
            } catch (Exception e) {
                throw new CandidateStatusNotFoundErrorException(String.format("Candidate with Status: [%s] is not existing to the database!", buildEmailDTO.getStatus()));
            }
            EmailReportUtil.emailSentReport(list1, buildEmailDTO.getTemplate(), buildEmailDTO.getHr(), buildEmailDTO.getFollowUpDate(), buildEmailDTO.getSubject(), emailReportRepository);
            return new ResponseEntity<>(sendingEmailUtil.processSendingEmails(sender, buildEmailDTO.getSubject(), EmailUtility.formatHTMLbody(templateRepository.findTemplateTitle(buildEmailDTO.getTemplate())), true), HttpStatus.OK);
        }
//        EmailReportUtil.emailSentReport(list, buildEmailDTO.getHr(), buildEmailDTO.getSubject(), emailReportRepository);
        return new ResponseEntity<>(sendingEmailUtil.processSendingEmails(sender, buildEmailDTO.getSubject(), buildEmailDTO.getMsgBody(), false), HttpStatus.OK);
    }

    @SneakyThrows
    @Override
    public ResponseEntity exportEmailReport(Long id) {

        try{
            String filename = "Export_Email_Report";
            return new ResponseEntity<>(ExcelUtility.export(id, filename, emailReportRepository), HttpStatus.OK);
        } catch (RuntimeException | IOException e) {
            throw new ExportEmailReportErrorException(String.format("Email Report ID: %d is not existing to the database!", id));
        }
    }
}