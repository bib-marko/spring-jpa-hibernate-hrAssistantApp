package com.example.candidaterestv2.service;

import com.example.candidaterestv2.model.Company;

import java.util.Optional;


public interface CompanyService {

    Company upsertCompanyDetails(Company company);

    Optional<Company> getCompanyDetails();
}
