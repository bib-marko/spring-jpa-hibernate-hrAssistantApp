package com.bib.hrassistantapp.repository;

import com.bib.hrassistantapp.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface XlsCandidateRepository extends JpaRepository<Candidate, Long> {

    @Query(value = "SELECT c FROM Candidate c WHERE c.uuid = :uuid")
    Optional<Candidate> findByUuid(@Param("uuid") Long uuid);

}
