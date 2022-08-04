package com.bib.hrassistantapp.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import com.bib.hrassistantapp.advice.EmailSendingFailedException;
import com.bib.hrassistantapp.model.Candidate;
import com.bib.hrassistantapp.model.EmailDetails;
import com.bib.hrassistantapp.model.EmailReport;
import com.bib.hrassistantapp.repository.CandidateRepository;
import com.bib.hrassistantapp.repository.EmailReportRepository;
import com.bib.hrassistantapp.repository.TemplateRepository;
import com.bib.hrassistantapp.service.EmailService;
import com.bib.hrassistantapp.utils.EmailUtility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.mail.SimpleMailMessage;
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

    public EmailServiceImpl(JavaMailSender javaMailSender, TemplateRepository templateRepository, CandidateRepository candidateRepository, EmailReportRepository emailReportRepository) {
        this.javaMailSender = javaMailSender;
        this.templateRepository = templateRepository;
        this.candidateRepository = candidateRepository;
        this.emailReportRepository = emailReportRepository;
    }

    public ResponseEntity<String> sendSimpleMail(EmailDetails details)
    {
        String [] recipient = details.getRecipient().replaceAll(" ", "").split(",");
        return sendMessage(recipient, details, "to");
    }

    @Override
    public ResponseEntity<String> sendSimpleMailWithCC(EmailDetails details) {
        String [] recipient = details.getRecipient().replaceAll(" ", "").split(",");
        return sendMessage(recipient, details, "cc");
    }

    @Override
    public ResponseEntity<String> sendSimpleMailWithBCC(EmailDetails details) {
        String [] recipient = details.getRecipient().replaceAll(" ", "").split(",");
        return sendMessage(recipient, details, "bcc");
    }

    private ResponseEntity<String> sendMessage(String[] recipient, EmailDetails details, String emailType) {
        try {
            SimpleMailMessage mailMessage= new SimpleMailMessage();
            mailMessage.setFrom(sender);
            if(emailType.equals("to")){
                mailMessage.setTo(recipient);
            }else if(emailType.equals("cc")){
                mailMessage.setCc(recipient);
            }else{
                mailMessage.setBcc(recipient);
            }
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
            javaMailSender.send(mailMessage);
            return new ResponseEntity<>("Mail sent Successfully", HttpStatus.OK);
        }catch (Exception e) {
            throw new EmailSendingFailedException();
        }

    }

    public ResponseEntity<String> sendMailWithAttachment(EmailDetails details){

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            String [] rcp = details.getRecipient().split(",");
            mimeMessageHelper.setTo(rcp);
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(details.getSubject());

            FileSystemResource file= new FileSystemResource(new File(details.getAttachment()));
            mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
            javaMailSender.send(mimeMessage);
            return new ResponseEntity<>("Mail sent Successfully", HttpStatus.OK);
        } catch (MessagingException e) {
            throw new EmailSendingFailedException();
        }
    }

    @Override
    public ResponseEntity<String> sendMailWithHTML(String position, String status, String subject, String template, String hr, String followUpDate) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            position = position.equals("all") ? " " : position;
            List<Candidate> list = candidateRepository.findAll("", position, status);
            mimeMessageHelper.setTo(EmailUtility.formatEmails(list));
            mimeMessageHelper.setText(EmailUtility.formatHTMLbody(templateRepository.findTemplateTitle(template)), true);
            mimeMessageHelper.setSubject(subject);
            javaMailSender.send(mimeMessage);
            emailSentReport(list, hr, followUpDate);
            return new ResponseEntity<>("Mail sent Successfully", HttpStatus.OK);
        } catch (MessagingException e) {
            throw new EmailSendingFailedException();
        }
    }

    private void emailSentReport(List<Candidate> list, String hr, String followUpDate) {
        List<EmailReport> emailReports = new ArrayList<>();
        for (Candidate details : list) {
            EmailReport emailReportDetails = new EmailReport();
            emailReportDetails.setFullname(details.getFullName());
            emailReportDetails.setEmail(details.getEmail());
            emailReportDetails.setStatus(details.getOverallStatus());
            emailReportDetails.setHr(hr);
            emailReportDetails.setLastFollowUpdate(followUpDate);
            emailReports.add(emailReportDetails);
        }
        emailReportRepository.saveAll(emailReports);
    }

}