package com.bib.hrassistantapp.service.impl;

import com.bib.hrassistantapp.advice.TemplateNotExistingErrorException;
import com.bib.hrassistantapp.advice.TemplateTitleErrorException;
import com.bib.hrassistantapp.model.Template;
import com.bib.hrassistantapp.repository.TemplateRepository;
import com.bib.hrassistantapp.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
            throwTemplateTitleErrorException(template.getTitle());
        }
        template.setId(template.getId());
        templateRepository.save(template);
        return new ResponseEntity<>(template, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteEmailTemplate(Long id) {
        if(!templateRepository.findById(id).isPresent()){
            throwTemplateNotExistingErrorException(id);
        }
        templateRepository.deleteById(id);
        return new ResponseEntity<>("Email Template Number: " +  id + " is successfully deleted!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateEmailTemplate(Template template) {
        if(!templateRepository.findById(template.getId()).isPresent()){
            throwTemplateNotExistingErrorException(template.getId());
        }
        template.setCreatedAt(templateRepository.findById(template.getId()).get().getCreatedAt());
        templateRepository.save(template);
        return new ResponseEntity<>("Email Template Number: " +  template.getId() + " is successfully updated!", HttpStatus.OK);
    }

    private void throwTemplateNotExistingErrorException(Long id) { throw new TemplateNotExistingErrorException(String.format("Email Template Id: %d is not Existing!", id));}
    private void throwTemplateTitleErrorException(String title) {throw new TemplateTitleErrorException(String.format("Email Template Title: %s is Already Existing, Please provide some unique Title.", title));}


}

