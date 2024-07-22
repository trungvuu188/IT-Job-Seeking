package com.jobseeking.jobseekingbe.entity;

import com.jobseeking.jobseekingbe.entity.keys.KeyEmployerCandidate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity(name = "candidate_save")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateSave {

    @EmbeddedId
    KeyEmployerCandidate keyEmployerCandidate;

    @ManyToOne
    @JoinColumn(name = "candidate_id", insertable=false, updatable=false)
    Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "employer_id", insertable=false, updatable=false)
    Employer employer;

    @Lob
    @Column(name = "cv_data")
    String cvData;
}
