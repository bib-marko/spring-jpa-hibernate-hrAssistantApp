package com.bib.hrassistantapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@Table
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @JsonProperty("job_title")
    @NotBlank(message = "Job Title is a required field.")
    private String jobTitle;

    @Column
    @JsonProperty("job_group")
    @NotBlank(message = "Job Group is a required field.")
    private String jobGroup;

    @Column
    @JsonProperty("job_desc")
    @NotBlank(message = "Job Description is a required field.")
    private String jobDesc;

    @Column
    @JsonProperty("created_at")
    private Date createdAt;

    @Column
    @JsonProperty("updated_at")
    private Date updatedAt;

    @Column
    @NotBlank(message = "Status is a required field.")
    private String status;

    @PrePersist
    public void prePersist(){
        setCreatedAt(new Date());
        setUpdatedAt(new Date());
    }

    @PreUpdate
    public void preUpdate(){
        setUpdatedAt(new Date());
    }
}
