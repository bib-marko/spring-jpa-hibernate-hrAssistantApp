package com.bib.hrassistantapp.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BuildEmailDTO {


    private String recipientTO;
    private String recipientBCC;
    private String recipientCC;

    private String msgBody;

    private String subject;

    private String attachment;

    private String position;
    private String status;
    private String template;
    private String hr;
    private String followUpDate;
}
