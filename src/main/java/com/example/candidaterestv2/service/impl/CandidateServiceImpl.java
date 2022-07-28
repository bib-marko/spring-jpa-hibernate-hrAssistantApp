package com.example.candidaterestv2.service.impl;

import com.example.candidaterestv2.model.Candidate;
import com.example.candidaterestv2.repository.CandidateRepository;
import com.example.candidaterestv2.service.CandidateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public Candidate insertCandidate(Candidate candidate) {

        return candidateRepository.save(candidate);
    }

    @Override
    public Candidate updateCandidate(Candidate candidate) {
        return null;
    }

    @Override
    public Map<String, Boolean> deleteCandidate(Long id) {
        return null;
    }

    @Override
    public List<Candidate> getCandidates() {
        return candidateRepository.findAll();
    }

    @Override
    public Candidate findCandidateById(Long id) {
        return null;
    }

}

