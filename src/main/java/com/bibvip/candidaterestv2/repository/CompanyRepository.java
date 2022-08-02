package com.bibvip.candidaterestv2.repository;

import com.bibvip.candidaterestv2.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
