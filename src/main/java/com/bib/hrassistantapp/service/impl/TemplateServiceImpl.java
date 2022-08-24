package com.bib.hrassistantapp.service.impl;

import com.bib.hrassistantapp.advice.TemplateNotExistingErrorException;
import com.bib.hrassistantapp.advice.TemplateTitleErrorException;
import com.bib.hrassistantapp.model.Template;
import com.bib.hrassistantapp.repository.TemplateRepository;
import com.bib.hrassistantapp.service.TemplateService;
import com.bib.hrassistantapp.utils.PageOption;
import com.bib.hrassistantapp.utils.TemplateUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository templateRepository;

    public TemplateServiceImpl(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public List<Template> findAll() {
        return templateRepository.findAll();
    }

    @Override
    public ResponseEntity<Template> insert(Template template) {
        if (templateRepository.findTemplateTitle(template.getTitle()) != null) {
            throw new TemplateTitleErrorException(String.format("Email Template Title: %s is Already Existing, Please provide some unique Title.", template.getTitle()));
        }
        template.setBody(TemplateUtility.removeDoctye(template.getBody()));
        templateRepository.save(template);
        return new ResponseEntity<>(template, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<String> deleteEmailTemplate(Long id) {
        if(!templateRepository.findById(id).isPresent()){
            throw new TemplateNotExistingErrorException(String.format("Email Template Id: %d is not Existing!", id));
        }
        templateRepository.deleteById(id);
        return new ResponseEntity<>("Email Template Number: " +  id + " is successfully deleted!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateEmailTemplate(Template template) {
        if(!templateRepository.findById(template.getId()).isPresent()){
            throw new TemplateNotExistingErrorException(String.format("Email Template Id: %d is not Existing!", template.getId()));
        }
        template.setCreatedAt(templateRepository.findById(template.getId()).get().getCreatedAt());
        templateRepository.save(template);
        return new ResponseEntity<>("Email Template Number: " +  template.getId() + " is successfully updated!", HttpStatus.OK);
    }

    @Override
    public List<String> getEmailTemplate(String templateBody) {
        return Collections.singletonList(templateRepository.findTemplateTitle(templateBody));
    }

    @Override
    public Page<Template> getTemplateWithPaginationAndFilters(PageOption pageOption) {
        Integer page = pageOption.getPage();
        Integer pageSize = pageOption.getPageSize();
        String title = pageOption.getPositionFilter();

        return templateRepository.findAll(title, PageRequest.of(--page,pageSize));
    }

}
