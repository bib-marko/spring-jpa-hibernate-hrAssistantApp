package com.bib.hrassistantapp.service.impl;

import com.bib.hrassistantapp.advice.EntityAlreadyExistException;
import com.bib.hrassistantapp.advice.EntityNotFoundException;
import com.bib.hrassistantapp.model.Job;
import com.bib.hrassistantapp.repository.JobRepository;
import com.bib.hrassistantapp.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public ResponseEntity<List<Job>> findAll() {
        List<Job> list = jobRepository.findAll();

        if (list == null || list.isEmpty()) {
            throw new EntityNotFoundException("There are no jobs in the system.");
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public Job addJob(Job job) {

        Job existingJob = jobRepository.findByTitle(job.getJobTitle());
        if(existingJob != null) {
            throw new EntityAlreadyExistException(String.format("Job with the job title [%s] already exist.", job.getJobTitle()));
        }
        return jobRepository.save(job);
    }

    @Override
    public ResponseEntity<String> deleteJob(Long id) {

        Optional<Job> existingJob = jobRepository.findById(id);
        if(!existingJob.isPresent()) {
            throw new EntityNotFoundException(String.format("Job with id [%d] not found.", id));
        }
        jobRepository.deleteById(id);
        return new ResponseEntity<>(String.format("Job with id [%d] deleted.",id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Job> updateJob(Long id, Job job) {

        Optional<Job> existingJob = jobRepository.findById(id);
        if(!existingJob.isPresent()) {
            throw new EntityNotFoundException(String.format("Job with id [%d] not found.", id));
        }
        job.setId(id);
        job.setCreatedAt(existingJob.get().getCreatedAt());
        return new ResponseEntity<>(jobRepository.save(job), HttpStatus.OK);

    }


}

