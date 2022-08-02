package com.example.candidaterestv2.service.impl;

import com.example.candidaterestv2.advice.EntityAlreadyExistException;
import com.example.candidaterestv2.advice.EntityNotFoundException;
import com.example.candidaterestv2.model.Candidate;
import com.example.candidaterestv2.repository.CandidateRepository;
import com.example.candidaterestv2.service.CandidateService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public Candidate insertCandidate(Candidate candidate) {
        try{
            return candidateRepository.save(candidate);
        }catch (DataIntegrityViolationException e){
            throw new EntityAlreadyExistException("Candidate uuid ["+candidate.getUuid()+"] already exist");
        }
    }

    @Override
    public Candidate updateCandidate(Candidate candidate) {
        Optional<Candidate> oldCandidate = candidateRepository.findById(candidate.getId());
        if(!oldCandidate.isPresent()){
            throw new EntityNotFoundException("Candidate with ID ["+candidate.getId()+"] not found");
        }
        candidate.setCreatedAt(oldCandidate.get().getCreatedAt());
        return candidateRepository.save(candidate);
    }

    @Override
    public ResponseEntity<String> deleteCandidate(Long id) {
        Optional<Candidate> oldCandidate = candidateRepository.findById(id);
        if(!oldCandidate.isPresent()){
            throw new EntityNotFoundException("Candidate with ID ["+id+"] not found");
        }
        candidateRepository.deleteById(id);
        return new ResponseEntity<>("Candidate with ID ["+id+"] deleted", HttpStatus.OK);
    }

    @Override
    public List<Candidate> getCandidates() {
        return candidateRepository.findAll();
    }

    @Override
    public List<Candidate> getCandidatesByPosition(String position) {
        return candidateRepository.findByPosition(position);
    }

    @Override
    public List<Candidate> getCandidatesByFullName(String fullName) {
        return candidateRepository.findByFullNameContaining(fullName);
    }

    @Override
    public Page<Candidate> getCandidateWithPagination(int offset, int pageSize) {
        Page<Candidate> candidates = candidateRepository.findAll(PageRequest.of(offset,pageSize));

        return candidates;
    }

    @Override
    public Page<Candidate> getCandidateWithPaginationAndSort(int offset, int pageSize, String sort) {
        Page<Candidate> candidates = candidateRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(sort)));
        return candidates;
    }

}

