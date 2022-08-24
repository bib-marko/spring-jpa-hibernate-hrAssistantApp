package com.bib.hrassistantapp.controller;

import com.bib.hrassistantapp.model.Candidate;
import com.bib.hrassistantapp.model.Template;
import com.bib.hrassistantapp.service.TemplateService;
import com.bib.hrassistantapp.utils.PageOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/template")
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping(value = "/list")
    List<Template> getAllTemplate() {
        return templateService.findAll();
    }

    @PostMapping(value = "/new")
    ResponseEntity<Template> newEmailTemplate(@Valid @RequestBody Template template) {
        return templateService.insert(template);
    }

    @PostMapping(value = "/delete/{id}")
    ResponseEntity<String> deleteEmailTemplate(@PathVariable Long id) {
        return templateService.deleteEmailTemplate(id);
    }

    @PostMapping(value = "/update/{id}")
    ResponseEntity<String> updateEmailTemplate(@PathVariable(value = "id") Long id, @Valid @RequestBody Template template) {
        template.setId(id);
        return templateService.updateEmailTemplate(template);
    }

    @GetMapping("/getEmailTemplate")
    public List<String> getEmailTemplate(@RequestParam("title") String title)
    {
        return templateService.getEmailTemplate(title);
    }

    @GetMapping("/pagination")
    public Page<Template> getCandidateWithPagination(@RequestParam Optional<Integer> page, @RequestParam("page_size") Optional<Integer> pageSize,
                                                      @RequestParam("title") Optional<String> title )
    {
        PageOption pageOption = new PageOption();
        pageOption.setPage(page.orElse(1));
        pageOption.setPageSize(pageSize.orElse(10));
        pageOption.setPositionFilter(title.orElse(""));
        return templateService.getTemplateWithPaginationAndFilters(pageOption);
    }

}