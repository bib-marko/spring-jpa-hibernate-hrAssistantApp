package com.example.candidaterestv2.repository;

import com.example.candidaterestv2.model.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    @Query("SELECT c FROM Candidate c WHERE c.uuid = :uuid")
    Candidate findByUuid(Long uuid);

    List<Candidate> findByPosition(String position);

    List<Candidate> findByFullNameContaining(String fullName);

    Page<Candidate> findByFullNameContaining(String fullName, Pageable pageable);
}
