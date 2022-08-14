package com.bib.hrassistantapp.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EmailSentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String email;

//    EmailSentHistory (Position and Status should be in type of Enum)

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String status;

    @Column(name = "last_follow_update", nullable = false)
    private String lastFollowUpdate;

    @Column(nullable = false)
    private Date createdAt;

    @PrePersist
    public void onPrePersist () {
        if (this.getCreatedAt() == null)
        this.setCreatedAt(new Date());
    }

}
