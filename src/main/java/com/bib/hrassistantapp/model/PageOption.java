package com.bib.hrassistantapp.model;

import lombok.Data;

@Data
public class PageOption extends ListOption{
    Integer page;
    Integer pageSize;
}
