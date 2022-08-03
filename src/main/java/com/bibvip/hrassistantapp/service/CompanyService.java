package com.bibvip.hrassistantapp.service;

import com.bibvip.hrassistantapp.model.Company;

import java.util.Optional;


public interface CompanyService {

    Company upsertCompanyDetails(Company company);

    Optional<Company> getCompanyDetails();
}
