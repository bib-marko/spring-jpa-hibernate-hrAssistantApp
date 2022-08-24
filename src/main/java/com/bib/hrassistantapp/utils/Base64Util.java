package com.bib.hrassistantapp.utils;

import lombok.Data;

import java.util.Base64;

@Data
public class Base64Util {

    String encoded;
    String decoded;

    public static String encodeBase64(byte[] bArray) {
        return Base64.getEncoder().encodeToString(bArray);
    }

    public static byte[] decode64(String base64Data) {
        return Base64.getDecoder().decode(base64Data);
    }
}
