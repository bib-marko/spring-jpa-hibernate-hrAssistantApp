package com.bib.hrassistantapp.service;

import com.bib.hrassistantapp.model.Candidate;
import com.bib.hrassistantapp.utils.ListOption;
import com.bib.hrassistantapp.utils.PageOption;
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
