package com.bib.hrassistantapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EnableJpaAuditing
@Table(name = "Candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    @JsonProperty("full_name")
    @NotBlank(message = "Full Name is mandatory")
    private String fullName;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    @JsonProperty("contact_number")
    private String contactNumber;

    @Column(nullable = false, unique = true)
    @Email(message = "Email must be valid")
    private String email;

    @Column(nullable = false)
    @JsonProperty("date_endorsed")
    private Date dateEndorsed;

    @Column(name = "overall_status", nullable = false)
    @JsonProperty("overall_status")
    private String overallStatus;

    @JsonProperty("hiring_manager")
    private String hiringManager;

    @JsonProperty("paper_screening_status")
    private String paperScreeningStatus;

    @JsonProperty("tech_interview_schedule")
    private Date techInterviewSchedule;

    @JsonProperty("interview_result")
    private String interviewResult;

    @JsonProperty("offer_status")
    private String offerStatus;

    @JsonProperty("offer_date")
    private Date offerDate;

    @JsonProperty("on_boarding_date")
    private Date onBoardingDate;

    @JsonProperty("is_rejection_email_sent")
    private Boolean isRejectionEmailSent;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;

    @PrePersist
    public void onPrePersist() {
        setCreatedAt(new Date());
        setUpdatedAt(new Date());
    }
    @PreUpdate
    public void onPreUpdate () {
        setUpdatedAt (new Date ());
    }

}
