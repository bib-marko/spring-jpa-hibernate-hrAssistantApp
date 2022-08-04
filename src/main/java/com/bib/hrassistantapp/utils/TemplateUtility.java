package com.bib.hrassistantapp.utils;

public class TemplateUtility {

    public static String removeDoctye(String body) {
        return body.replaceAll("(?i)<!DOCTYPE[^<>]*(?:<!ENTITY[^<>]*>[^<>]*)?>", "");
    }

}
