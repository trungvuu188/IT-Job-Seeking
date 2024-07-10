package com.jobseeking.jobseekingbe.entity.keys;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KeyEmployerCandidate implements Serializable {

    @Column(name = "candidate_id")
    String candidateId;

    @Column(name = "employer_id")
    String employerId;
}
