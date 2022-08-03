package com.bib.hrassistantapp.repository;

import com.bib.hrassistantapp.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public interface TemplateRepository extends JpaRepository<Template, Long> {
    @Query(value = "SELECT t FROM Template t WHERE t.title=:title")
    Template findTemplateTitle(@Param("title") String title);
}
