package com.bib.hrassistantapp.controller;

import com.bib.hrassistantapp.advice.CustomResponseMessage;
import com.bib.hrassistantapp.model.Candidate;
import com.bib.hrassistantapp.service.XlsCandidateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/excel/candidate")
public class XlsCandidateController {

    private final XlsCandidateService xlsCandidateService;

    public XlsCandidateController(XlsCandidateService xlsCandidateService) {
        this.xlsCandidateService = xlsCandidateService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Candidate>> showCandidates(){
        return new ResponseEntity<>(xlsCandidateService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/import/")
    public ResponseEntity<CustomResponseMessage> importExcel(@RequestParam MultipartFile file) throws IOException {

        return xlsCandidateService.importExcel(file);

    }


}
