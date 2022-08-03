package com.bib.hrassistantapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Company{
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(columnDefinition = "MEDIUMTEXT")
    @NotBlank(message = "Company information is required to fill up")
    private String information;

    @Column(columnDefinition = "MEDIUMTEXT")
    @NotBlank(message = "Company disclaimer is required to fill up")
    private String disclaimer;

    @Column(nullable = false,updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
