package com.bibvip.hrassistantapp.controller;

import com.bibvip.hrassistantapp.model.Candidate;
import com.bibvip.hrassistantapp.model.ListOption;
import com.bibvip.hrassistantapp.model.PageOption;
import com.bibvip.hrassistantapp.service.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/candidate/")
@Validated
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/save")
    public Candidate createCandidate(@Valid @RequestBody Candidate candidate)
    {
        return candidateService.insertCandidate(candidate);
    }

    @PutMapping("/update/{id}")
    public Candidate updateCandidate(@PathVariable Long id, @Valid @RequestBody Candidate candidate)
    {
        candidate.setId(id);
        return candidateService.updateCandidate(candidate);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCandidate(@PathVariable Long id)
    {
        return candidateService.deleteCandidate(id);
    }

    @GetMapping("/list")
    public List<Candidate> getAllCandidateEmailWithFilters(@RequestParam Optional<String> position, @RequestParam("full_name") Optional<String> fullName,
                                                        @RequestParam("overall_status") Optional<String> overallStatus)
    {
        ListOption listOption = new ListOption();
        listOption.setPositionFilter(position.orElse(""));
        listOption.setFullNameSearch(fullName.orElse(""));
        listOption.setOverallStatusFilter(overallStatus.orElse(""));
        return candidateService.getCandidatesEmail(listOption);
    }


    @GetMapping("/pagination/")
    public Page<Candidate> getCandidateWithPagination(@RequestParam Optional<Integer> page, @RequestParam("page_size") Optional<Integer> pageSize,
                                                      @RequestParam Optional<String> position, @RequestParam("full_name") Optional<String> fullName )
    {
        PageOption pageOption = new PageOption();
        pageOption.setPage(page.orElse(1));
        pageOption.setPageSize(pageSize.orElse(10));
        pageOption.setPositionFilter(position.orElse(""));
        pageOption.setFullNameSearch(fullName.orElse(""));

        Page<Candidate> candidateWithPagination = candidateService.getCandidateWithPaginationAndFilters(pageOption);
        return candidateWithPagination;
    }
}
