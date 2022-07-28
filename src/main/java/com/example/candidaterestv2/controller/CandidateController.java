package com.example.candidaterestv2.controller;

import com.example.candidaterestv2.model.Candidate;
import com.example.candidaterestv2.service.CandidateService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("candidate/")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/save")
    public Candidate createCandidate(@Valid @RequestBody Candidate candidate)
    {
        return candidateService.insertCandidate(candidate);
    }

    @GetMapping("/list")
    public List<Candidate> getAllCandidate()
    {
        return candidateService.getCandidates();
    }

    @PutMapping("/update/{id}")
    public Candidate updateCandidate(@PathVariable Long id, @Valid @RequestBody Candidate candidate)
    {
        candidate.setId(id);
        return candidateService.updateCandidate(candidate);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCandidate(@PathVariable Long id)
    {
        return candidateService.deleteCandidate(id);
    }


}
