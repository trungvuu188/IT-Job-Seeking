package com.jobseeking.jobseekingbe.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Entity(name = "post_apply_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostApplyStatus {

    @Id
    @Column(name = "status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int statusId;

    @Column(name = "status_title")
    String statusTitle;

    @OneToMany(mappedBy = "postApplyStatus")
    Set<PostApply> postApplySet;

    @OneToMany(mappedBy = "postApplyStatus")
    Set<CandidateApply> candidateApplies;
}
