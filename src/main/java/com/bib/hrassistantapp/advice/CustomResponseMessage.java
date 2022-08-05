package com.bib.hrassistantapp.advice;

import com.bib.hrassistantapp.model.Candidate;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CustomResponseMessage {
    List<Candidate> insert;
    List<Candidate> update;
    List<String> invalid;
    Date timestamp;
}

