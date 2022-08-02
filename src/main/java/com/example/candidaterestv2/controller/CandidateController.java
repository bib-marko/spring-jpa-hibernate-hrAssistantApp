package com.example.candidaterestv2.controller;

import com.example.candidaterestv2.model.Candidate;
import com.example.candidaterestv2.service.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/candidate/")
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

    @GetMapping("/list")
    public List<Candidate> getAllCandidate()
    {
        return candidateService.getCandidates();
    }

    @GetMapping("/list/position/{position}")
    public List<Candidate> getAllCandidateByPosition(@PathVariable String position)
    {
        return candidateService.getCandidatesByPosition(position);
    }

    @GetMapping("/list/full_name/{full_name}")
    public List<Candidate> getAllCandidateByFullName(@PathVariable("full_name") String fullName)
    {
        return candidateService.getCandidatesByFullName(fullName);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public Page<Candidate> getCandidateWithPagination(@PathVariable int offset, @PathVariable int pageSize)
    {
        Page<Candidate> candidateWithPagination = candidateService.getCandidateWithPagination(--offset, pageSize);
        return candidateWithPagination;
    }
    @GetMapping("/pagination/{offset}/{pageSize}/{sort}")
    public Page<Candidate> getCandidateWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String sort)
    {
        Page<Candidate> candidateWithPagination = candidateService.getCandidateWithPaginationAndSort(--offset, pageSize, sort);
        return candidateWithPagination;
    }


}
