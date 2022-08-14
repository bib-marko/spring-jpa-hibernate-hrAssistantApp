package com.bib.hrassistantapp.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BuildEmailDTO {
    String position;
    String status;
    String subject;
    String template;
    String hr;
    String followUpDate;
}
