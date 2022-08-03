package com.bib.hrassistantapp.controller;

import com.bib.hrassistantapp.model.Job;
import com.bib.hrassistantapp.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/job")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Job>> findAll(){
        return jobService.findAll();
    }

    @PostMapping("/new")
    public Job addJob(@Valid @RequestBody Job job){
        return jobService.addJob(job);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        return jobService.deleteJob(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @Valid @RequestBody Job job){
        return jobService.updateJob(id,job);
    }

}
