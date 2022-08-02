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

    List<Candidate> getCandidatesByPosition(String position);

    List<Candidate> getCandidatesByFullName(String fullName);

    Page<Candidate> getCandidateWithPagination(int offset, int pageSize);

    //Page<Candidate> getCandidateWithPaginationAndSearch(int offset, int pageSize, String search);

    Page<Candidate> getCandidateWithPaginationAndSort(int offset, int pageSize, String sort);
}
