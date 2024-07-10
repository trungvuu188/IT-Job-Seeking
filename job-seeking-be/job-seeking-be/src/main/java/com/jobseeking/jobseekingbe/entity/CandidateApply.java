package com.jobseeking.jobseekingbe.entity;

import com.jobseeking.jobseekingbe.entity.keys.KeyEmployerCandidate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "candidate_apply")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(KeyEmployerCandidate.class)
public class CandidateApply {

    @Id
    @Column(name = "candidate_id")
    String candidateId;

    @Id
    @Column(name = "employer_id")
    String employerId;
}
