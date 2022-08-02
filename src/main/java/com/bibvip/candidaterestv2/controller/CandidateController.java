package com.bibvip.candidaterestv2.controller;

import com.bibvip.candidaterestv2.model.Candidate;
import com.bibvip.candidaterestv2.model.ListOrPageOption;
import com.bibvip.candidaterestv2.service.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/candidate/")
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
    public List<Candidate> getAllCandidate(@RequestParam Optional<String> position, @RequestParam("full_name") Optional<String> fullName)
    {
        ListOrPageOption listOrPageOption = new ListOrPageOption();
        listOrPageOption.setPositionFilter(position.orElse(" "));
        listOrPageOption.setFullNameSearch(fullName.orElse(" "));
        return candidateService.getCandidates(listOrPageOption);
    }


    @GetMapping("/pagination/")
    public Page<Candidate> getCandidateWithPagination(@RequestParam Optional<Integer> offset, @RequestParam("page_size") Optional<Integer> pageSize,
                                                        @RequestParam Optional<String> position, @RequestParam("full_name") Optional<String> fullName )
    {
        ListOrPageOption listOrPageOption = new ListOrPageOption();
        listOrPageOption.setOffset(offset.orElse(0));
        listOrPageOption.setPageSize(pageSize.orElse(10));
        listOrPageOption.setPositionFilter(position.orElse(" "));
        listOrPageOption.setFullNameSearch(fullName.orElse(" "));

        Page<Candidate> candidateWithPagination = candidateService.getCandidateWithPaginationAndFilters(listOrPageOption);
        return candidateWithPagination;
    }
}
