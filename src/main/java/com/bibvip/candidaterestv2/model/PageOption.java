package com.bibvip.candidaterestv2.model;

import lombok.Data;

@Data
public class PageOption extends ListOption{
    Integer offset;
    Integer pageSize;
}