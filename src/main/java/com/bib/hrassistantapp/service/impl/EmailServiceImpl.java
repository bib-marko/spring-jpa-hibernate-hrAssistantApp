package com.bib.hrassistantapp.service.impl;

import java.io.File;
import java.util.Objects;


import com.bib.hrassistantapp.advice.EmailSendingFailedException;
import com.bib.hrassistantapp.model.EmailDetails;
import com.bib.hrassistantapp.repository.CandidateRepository;
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

    public EmailServiceImpl(JavaMailSender javaMailSender, TemplateRepository templateRepository, CandidateRepository candidateRepository) {
        this.javaMailSender = javaMailSender;
        this.templateRepository = templateRepository;
        this.candidateRepository = candidateRepository;

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
    public ResponseEntity<String> sendMailWithHTML(String position, String template, String hr, String followUpDate, EmailDetails details) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(EmailUtility.formatEmails(candidateRepository.findAll("", position, "")));
            mimeMessageHelper.setText(EmailUtility.formatHTMLbody(templateRepository.findTemplateTitle(template)), true);
            mimeMessageHelper.setSubject(details.getSubject());
            javaMailSender.send(mimeMessage);
            return new ResponseEntity<>("Mail sent Successfully", HttpStatus.OK);
        } catch (MessagingException e) {
            throw new EmailSendingFailedException();
        }
    }

}