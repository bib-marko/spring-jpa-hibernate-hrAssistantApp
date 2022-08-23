package com.bib.hrassistantapp.utils;

import com.bib.hrassistantapp.advice.EmailSendingFailedException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Data
public class SendingEmailUtil {

    private final JavaMailSender javaMailSender;

    String [] recipientTO;
    String [] recipientCC;
    String [] recipientBCC;
    String haveAttachment;

    public SendingEmailUtil(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String processSendingEmails(String from, String subject, String msgBody, boolean withHTMLContent){

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;


        try {

            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);

            mimeMessageHelper.setSubject(subject);

            if (this.recipientTO != null) {
                mimeMessageHelper.setTo(this.getRecipientTO());
            }
            if (this.recipientCC != null) {
                mimeMessageHelper.setCc(this.getRecipientCC());
            }
            if (this.recipientBCC != null) {
                mimeMessageHelper.setBcc(this.getRecipientBCC());
            }

            if(withHTMLContent){
                mimeMessageHelper.setText(msgBody, true);
            }
            mimeMessageHelper.setText(msgBody);

            if (this.getHaveAttachment() != null) {
                FileSystemResource file = new FileSystemResource(new File(this.getHaveAttachment()));
                mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
            }


            //javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        } catch (MessagingException e) {
            throw new EmailSendingFailedException();
        }

    }

}
