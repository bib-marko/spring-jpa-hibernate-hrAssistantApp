package com.bib.hrassistantapp.utils;

import com.bib.hrassistantapp.model.Candidate;
import com.bib.hrassistantapp.model.EmailReport;
import com.bib.hrassistantapp.model.EmailSentHistory;
import com.bib.hrassistantapp.repository.EmailReportRepository;

import java.util.ArrayList;
import java.util.List;

public class EmailReportUtil {

    public  static void emailSentReport(List<Candidate> list, String template, String hrName, String followUpDate, String subject, EmailReportRepository emailReportRepository) {
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

    public static void emailSentReport(List<String> list, String hrName, String subject, EmailReportRepository emailReportRepository) {
        List<EmailSentHistory> emailSentHistoryList = new ArrayList<>();
        EmailReport emailReport = new EmailReport();
        emailReport.setHr(hrName);
        emailReport.setSubject(subject);

        for (String details : list) {
            EmailSentHistory emailReportDetails = new EmailSentHistory();
            emailReportDetails.setEmail(details);
            emailSentHistoryList.add(emailReportDetails);
        }

        emailReport.setEmailSentHistoryList(emailSentHistoryList);
        emailReportRepository.save(emailReport);
    }
}
