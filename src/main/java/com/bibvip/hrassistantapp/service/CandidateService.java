package com.bibvip.hrassistantapp.service;

import com.bibvip.hrassistantapp.model.Candidate;
import com.bibvip.hrassistantapp.model.ListOption;
import com.bibvip.hrassistantapp.model.PageOption;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CandidateService {
    Candidate insertCandidate(Candidate candidate);

    Candidate updateCandidate(Candidate candidate);

    ResponseEntity<String> deleteCandidate(Long id);

    List<Candidate> getCandidatesEmail(ListOption listOption);

    Page<Candidate> getCandidateWithPaginationAndFilters(PageOption pageOption);


}
