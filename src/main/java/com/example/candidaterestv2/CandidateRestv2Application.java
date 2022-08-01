package com.example.candidaterestv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication

public class CandidateRestv2Application {

    public static void main(String[] args) {
        SpringApplication.run(CandidateRestv2Application.class, args);
    }

}
