package com.bib.hrassistantapp.repository;

import com.bib.hrassistantapp.model.EmailSentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailSentHistoryRepository extends JpaRepository<EmailSentHistory, Long> {
}
