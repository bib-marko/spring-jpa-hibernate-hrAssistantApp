package com.example.candidaterestv2.repository;

import com.example.candidaterestv2.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    @Query("SELECT c FROM Candidate c WHERE c.uuid = :uuid")
    Candidate findByUuid(Long uuid);
}
