package com.bib.hrassistantapp.utils;

import lombok.Data;

import java.util.Base64;

@Data
public class Base64Util {

    String encoded;
    String decoded;

    public static String encodeBase64(byte[] bArray) {
        String encodedFile = Base64.getEncoder().encodeToString(bArray);
        return encodedFile;
    }

    public static byte[] decode64(String base64Data) {
        byte[] decodedFile = Base64.getDecoder().decode(base64Data);
        return decodedFile;
    }
}
