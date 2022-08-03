package com.bib.hrassistantapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EnableJpaAuditing
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    @JsonProperty("title")
    @NotBlank
    private String title;

    @Column(nullable = false)
    @JsonProperty("body")
    @NotBlank
    private String body;

    @Column(nullable = false)
    @JsonProperty("created_at")
    private Date createdAt;

    @Column(nullable = false)
    @JsonProperty("updated_at")
    private Date updatedAt;

    @PrePersist
    public void onPrePersist () {
        this.setCreatedAt(new Date());
        this.setUpdatedAt(new Date());
    }

    @PreUpdate
    public void onPreUpdate () {
        this.setUpdatedAt(new Date());
    }

}
