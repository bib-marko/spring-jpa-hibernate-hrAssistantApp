package com.bibvip.candidaterestv2.advice;

import lombok.Data;

import java.util.Date;

@Data
public class CustomErrorMessage {
    String status;
    String message;
    Date timestamp;
}
