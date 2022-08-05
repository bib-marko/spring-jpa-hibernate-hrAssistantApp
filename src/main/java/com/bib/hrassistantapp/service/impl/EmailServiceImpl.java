package com.bib.hrassistantapp.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


import com.bib.hrassistantapp.advice.EmailSendingFailedException;
import com.bib.hrassistantapp.advice.ExportEmailReportErrorException;
import com.bib.hrassistantapp.model.Candidate;
import com.bib.hrassistantapp.model.EmailDetails;
import com.bib.hrassistantapp.model.EmailReport;
import com.bib.hrassistantapp.model.EmailSentHistory;
import com.bib.hrassistantapp.repository.CandidateRepository;
import com.bib.hrassistantapp.repository.EmailReportRepository;
import com.bib.hrassistantapp.repository.EmailSentHistoryRepository;
import com.bib.hrassistantapp.repository.TemplateRepository;
import com.bib.hrassistantapp.service.EmailService;
import com.bib.hrassistantapp.utils.EmailReportExcelGenerator;
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
import javax.servlet.http.HttpServletResponse;


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
    public ResponseEntity<String> sendMailWithHTML(String position, String status, String subject, String template, String hrName, String followUpDate) {

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
            emailSentReport(list, template, hrName, followUpDate, subject);
            return new ResponseEntity<>("Mail sent Successfully", HttpStatus.OK);
        } catch (MessagingException e) {
            throw new EmailSendingFailedException();
        }
    }


    private void emailSentReport(List<Candidate> list, String template, String hrName, String followUpDate, String subject) {
        List<EmailSentHistory> emailSentHistoryList = new ArrayList<>();
        EmailReport emailReport = new EmailReport();
        emailReport.setHr(hrName);
        emailReport.setTemplate(template);
        emailReport.setSubject(subject);

        for (Candidate details : list) {
            EmailSentHistory emailReportDetails = new EmailSentHistory();
            emailReportDetails.setFullName(details.getFullName());
            emailReportDetails.setEmail(details.getEmail());
            emailReportDetails.setStatus(details.getOverallStatus());
            emailReportDetails.setPosition(details.getPosition());
            emailReportDetails.setLastFollowUpdate(followUpDate);

            emailSentHistoryList.add(emailReportDetails);
        }

        emailReport.setEmailSentHistoryList(emailSentHistoryList);
        emailReportRepository.save(emailReport);
    }

    @Override
    public ResponseEntity<String> exportEmailReport(HttpServletResponse response, Long id) {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=EmailReport-" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        try {
            Optional<EmailReport> emailReport = emailReportRepository.getEmailReportById(id);
            EmailReportExcelGenerator generator = new EmailReportExcelGenerator(emailReport);
            generator.generateExcelFile(response);
            return new ResponseEntity<>(String.format("Email Report ID: %d, is now successfully exported", id), HttpStatus.OK);
        } catch (RuntimeException | IOException e) {
            throw new ExportEmailReportErrorException(String.format("Email Report ID: %d is not existing to the database!", id));
        }
    }


}