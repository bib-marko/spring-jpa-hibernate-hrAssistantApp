package com.example.candidaterestv2.service;

import com.example.candidaterestv2.model.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface CandidateService {
    Candidate insertCandidate(Candidate candidate);

    Candidate updateCandidate(Candidate candidate);

    ResponseEntity<String> deleteCandidate(Long id);

    List<Candidate> getCandidates();

    Page<Candidate> getCandidateWithPagination(int offset, int pageSize);
}
