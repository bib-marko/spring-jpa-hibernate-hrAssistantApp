package com.bib.hrassistantapp.service;

import com.bib.hrassistantapp.model.Template;
import com.bib.hrassistantapp.utils.PageOption;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TemplateService {

    List<Template> findAll();
    ResponseEntity<Template> insert(Template template);
    ResponseEntity<String> deleteEmailTemplate(Long id);
    ResponseEntity<String> updateEmailTemplate(Template template);
    List<String> getEmailTemplate(String title);

    Page<Template> getTemplateWithPaginationAndFilters(PageOption pageOption);
}