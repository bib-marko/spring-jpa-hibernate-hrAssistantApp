package com.bib.hrassistantapp.service;

import com.bib.hrassistantapp.model.Company;

import java.util.Optional;


public interface CompanyService {

    Company upsertCompanyDetails(Company company);

    Optional<Company> getCompanyDetails();
}
