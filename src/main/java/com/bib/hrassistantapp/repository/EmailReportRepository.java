package com.bib.hrassistantapp.repository;

import com.bib.hrassistantapp.model.EmailReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailReportRepository extends JpaRepository<EmailReport, Long> {

    Optional<EmailReport> getEmailReportById(Long id);
}
