package com.bib.hrassistantapp.service;

import com.bib.hrassistantapp.advice.CustomResponseMessage;
import com.bib.hrassistantapp.model.Candidate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface XlsCandidateService {

    List<Candidate> findAll();

    ResponseEntity<CustomResponseMessage> importExcel(MultipartFile file) throws IOException;

}
