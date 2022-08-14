package com.bib.hrassistantapp.service.impl;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


import com.bib.hrassistantapp.advice.*;
import com.bib.hrassistantapp.model.Candidate;
import com.bib.hrassistantapp.model.EmailDetails;
import com.bib.hrassistantapp.model.EmailReport;
import com.bib.hrassistantapp.model.EmailSentHistory;
import com.bib.hrassistantapp.model.dto.BuildEmailDTO;
import com.bib.hrassistantapp.repository.CandidateRepository;
import com.bib.hrassistantapp.repository.EmailReportRepository;
import com.bib.hrassistantapp.repository.EmailSentHistoryRepository;
import com.bib.hrassistantapp.repository.TemplateRepository;
import com.bib.hrassistantapp.service.EmailService;
import com.bib.hrassistantapp.utils.*;
import lombok.SneakyThrows;
import lombok.var;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String sender;

    private final JavaMailSender javaMailSender;

    private final TemplateRepository templateRepository;

    private final CandidateRepository candidateRepository;

    private final EmailReportRepository emailReportRepository;



    public EmailServiceImpl(JavaMailSender javaMailSender, TemplateRepository templateRepository, CandidateRepository candidateRepository, EmailSentHistoryRepository emailSentHistoryRepository, EmailReportRepository emailReportRepository) {
        this.javaMailSender = javaMailSender;
        this.templateRepository = templateRepository;
        this.candidateRepository = candidateRepository;
        this.emailReportRepository = emailReportRepository;
    }

    public ResponseEntity<String> sendSimpleMail(EmailDetails details) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;


        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);

            if (details.getRecipientTO() != null) {
                mimeMessageHelper.setTo(EmailUtility.formatEmailsToArray(details.getRecipientTO()));
            }
            if (details.getRecipientCC() != null) {
                mimeMessageHelper.setCc(EmailUtility.formatEmailsToArray(details.getRecipientCC()));
            }
            if (details.getRecipientBCC() != null) {
                mimeMessageHelper.setBcc(EmailUtility.formatEmailsToArray(details.getRecipientBCC()));
            }

            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(details.getSubject());

            if (details.getAttachment() != null) {
                FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
                mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
            }

            //javaMailSender.send(mimeMessage);
            return new ResponseEntity<>("Mail sent Successfully", HttpStatus.OK);
        } catch (MessagingException e) {
            throw new EmailSendingFailedException();
        }
    }

    @Override
    public ResponseEntity<String> sendMailWithHTML(BuildEmailDTO buildEmailDTO) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);

            buildEmailDTO.setPosition(EmailUtility.validateJobPosition(buildEmailDTO.getPosition()));
            List<Candidate> list = candidateRepository.findAll("", buildEmailDTO.getPosition(), buildEmailDTO.getStatus());
            try {
                mimeMessageHelper.setTo(EmailUtility.formatEmails(list));
            } catch (MessagingException e) {
                throw new CandidateStatusNotFoundErrorException(String.format("Candidate with Status: [%s] is not existing to the database!", buildEmailDTO.getStatus()));
            }
            mimeMessageHelper.setText(EmailUtility.formatHTMLbody(templateRepository.findTemplateTitle(buildEmailDTO.getTemplate())), true);
            mimeMessageHelper.setSubject(buildEmailDTO.getSubject());
            EmailReportUtil.emailSentReport(list, buildEmailDTO.getTemplate(), buildEmailDTO.getHr(), buildEmailDTO.getFollowUpDate(), buildEmailDTO.getSubject(), emailReportRepository);
            return new ResponseEntity<>("Mail sent Successfully", HttpStatus.OK);
        } catch (MessagingException e) {
            throw new EmailSendingFailedException();
        }
    }
    


    @SneakyThrows
    @Override
    public ResponseEntity<String> exportEmailReport(Long id) {

        try{
            String filename = "Export_Email_Report";
            return new ResponseEntity(ExcelUtility.export(id, filename, emailReportRepository), HttpStatus.OK);
        } catch (RuntimeException | IOException e) {
            throw new ExportEmailReportErrorException(String.format("Email Report ID: %d is not existing to the database!", id));
        }
    }
}