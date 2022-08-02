package com.bibvip.candidaterestv2.service;

import com.bibvip.candidaterestv2.model.Candidate;
import com.bibvip.candidaterestv2.model.ListOrPageOption;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CandidateService {
    Candidate insertCandidate(Candidate candidate);

    Candidate updateCandidate(Candidate candidate);

    ResponseEntity<String> deleteCandidate(Long id);

    List<Candidate> getCandidates(ListOrPageOption listOrPageOption);

    List<Candidate> getCandidatesByPosition(String position);

    List<Candidate> getCandidatesByFullName(String fullName);

    Page<Candidate> getCandidateWithPaginationAndFilters(ListOrPageOption listOrPageOption);


}
