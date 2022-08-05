package com.bib.hrassistantapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EnableJpaAuditing
public class EmailSentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    @JsonProperty("full_name")
    private String fullName;

    @Column(nullable = false)
    @JsonProperty("email")
    private String email;

    @Column(nullable = false)
    @JsonProperty("position")
    private String position;

    @Column(nullable = false)
    @JsonProperty("status")
    private String status;

    @Column(name = "last_follow_update", nullable = false)
    private String lastFollowUpdate;

    @Column(nullable = false)
    @JsonProperty("created_at")
    private Date createdAt;

    @PrePersist
    public void onPrePersist () {
        this.setCreatedAt(new Date());
    }

}
