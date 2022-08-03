package com.bibvip.candidaterestv2.repository;

import com.bibvip.candidaterestv2.model.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    @Query("select c from Candidate c where c.position = ?1")
    List<Candidate> findByPosition(String position);

    List<Candidate> findByFullNameContaining(String fullName);

    @Query("select c from Candidate c " +
            "where c.fullName like concat('%', ?1, '%') and c.position like concat('%', ?2, '%')")
    Page<Candidate> findAll(String fullName, String position, Pageable pageable);

    @Query("select c from Candidate c " +
            "where c.fullName like concat('%', ?1, '%') and c.position like concat('%', ?2, '%') and c.overallStatus like concat('%', ?3, '%')")
    List<Candidate> findAll(String fullName, String position, String overallStatus);

}
