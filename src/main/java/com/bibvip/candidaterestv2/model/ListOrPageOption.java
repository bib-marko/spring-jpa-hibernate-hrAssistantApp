package com.bibvip.candidaterestv2.model;

import lombok.Data;

@Data
public class ListOrPageOption {
    Integer offset;
    Integer pageSize;
    String positionFilter;
    String fullNameSearch;
}
