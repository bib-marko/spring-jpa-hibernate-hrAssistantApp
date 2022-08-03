package com.bibvip.candidaterestv2.service;

import com.bibvip.candidaterestv2.model.Candidate;
import com.bibvip.candidaterestv2.model.ListOption;
import com.bibvip.candidaterestv2.model.PageOption;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CandidateService {
    Candidate insertCandidate(Candidate candidate);

    Candidate updateCandidate(Candidate candidate);

    ResponseEntity<String> deleteCandidate(Long id);

    List<String> getCandidatesEmail(ListOption listOption);

    Page<Candidate> getCandidateWithPaginationAndFilters(PageOption pageOption);


}
