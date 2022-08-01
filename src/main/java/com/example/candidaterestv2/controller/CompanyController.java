package com.example.candidaterestv2.controller;

import com.example.candidaterestv2.model.Company;
import com.example.candidaterestv2.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/company/")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/save")
    public Company upsertCompanyDetails(@Valid @RequestBody Company company)
    {
        company.setId(1L);
        return companyService.upsertCompanyDetails(company);
    }

    @GetMapping("/details")
    public Optional<Company> getCompanyDetails()
    {
        return companyService.getCompanyDetails();
    }
}
