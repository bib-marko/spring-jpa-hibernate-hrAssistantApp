package com.bib.hrassistantapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EnableJpaAuditing
public class EmailReport {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Long id;

       private String template;

        @Column(nullable = false)
        @JsonProperty("subject")
        private String subject;

        @Column(name = "hr_incharge", nullable = false)
        private String hr;

        @Column(nullable = false)
        @JsonProperty("created_at")
        private Date createdAt;

        @PrePersist
        public void onPrePersist () {
            this.setCreatedAt(new Date());
        }

        @OneToMany(targetEntity = EmailSentHistory.class, cascade = CascadeType.ALL)
        @JoinColumn(name = "email_report_id_fk", referencedColumnName = "id")
        private List<EmailSentHistory> emailSentHistoryList;





}
