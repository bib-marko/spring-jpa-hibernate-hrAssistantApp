package com.bib.hrassistantapp.service;

import com.bib.hrassistantapp.model.Job;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JobService {

    ResponseEntity<List<Job>> findAll();

    Job addJob(Job job);

    ResponseEntity<String> deleteJob(Long id);

    ResponseEntity<Job> updateJob(Long id, Job job);
}
