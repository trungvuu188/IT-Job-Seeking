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
public class KeyPostCandidate implements Serializable {
    @Column(name = "user_id")
    String userId;

    @Column(name = "post_id")
    int postId;
}
