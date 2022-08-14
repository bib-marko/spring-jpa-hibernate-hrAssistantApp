package com.bib.hrassistantapp.utils;

import com.bib.hrassistantapp.model.Candidate;
import java.util.List;

public class EmailUtility {


    public static String formatHTMLbody(String template) {
        return String.valueOf(template).replaceAll("[\\[\\]\\\\]", "");
    }

    public static String[] formatEmails(List<Candidate> position) {
        StringBuilder arr = new StringBuilder();
        for(Candidate str : position){
            arr.append(str.getEmail()).append(",");
        }
        return arr.toString().replaceAll(" ", "").split(",");
    }

    public static String[] formatEmailsToArray(String recipientTO) {
        return recipientTO.replaceAll(" ", "").split(",");
    }

    public static String validateJobPosition(String position) {
        return position.equals("all") ? " " : position;
    }

}
