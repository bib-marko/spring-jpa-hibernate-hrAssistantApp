package com.bibvip.hrassistantapp.service.impl;

import com.bibvip.hrassistantapp.model.Company;
import com.bibvip.hrassistantapp.repository.CompanyRepository;
import com.bibvip.hrassistantapp.service.CompanyService;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@EnableJpaAuditing
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company upsertCompanyDetails(Company company) {
        Optional<Company> oldCompanyDetails = companyRepository.findById(company.getId());
        if(!oldCompanyDetails.isPresent()){
            return companyRepository.save(company);
        }
        company.setCreatedAt(oldCompanyDetails.get().getCreatedAt());
        return companyRepository.save(company);
    }

    @Override
    public Optional<Company> getCompanyDetails() {
        return companyRepository.findById(1L);
    }
}
