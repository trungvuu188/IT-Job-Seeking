package com.jobseeking.jobseekingbe.entity;

import com.jobseeking.jobseekingbe.entity.keys.KeyEmployerCandidate;
import com.jobseeking.jobseekingbe.entity.keys.KeyPostCV;
import com.jobseeking.jobseekingbe.entity.keys.KeyPostCandidate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "candidate_apply")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateApply {

    @EmbeddedId
    KeyPostCandidate keyPostCandidate;

    @ManyToOne
    @JoinColumn(name = "post_id", insertable=false, updatable=false)
    Post post;

    @ManyToOne
    @JoinColumn(name = "candidate_id", insertable=false, updatable=false)
    Candidate candidate;

    @Lob
    @Column(name = "cv_data")
    String cvData;

    @ManyToOne
    @JoinColumn(name = "status_id")
    PostApplyStatus postApplyStatus;
}
