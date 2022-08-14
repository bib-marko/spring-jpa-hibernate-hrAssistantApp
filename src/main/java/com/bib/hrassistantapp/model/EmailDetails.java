package com.bib.hrassistantapp.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EmailDetails {

    private String recipientTO;
    private String recipientBCC;
    private String recipientCC;
    private String msgBody;
    private String subject;
    private String attachment;
}
