package com.bib.hrassistantapp.repository;

import com.bib.hrassistantapp.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface JobRepository extends JpaRepository<Job, Long> {

    @Query(value = "SELECT j FROM Job j WHERE j.jobTitle = :title")
    Job findByTitle(@Param("title") String title);
}
