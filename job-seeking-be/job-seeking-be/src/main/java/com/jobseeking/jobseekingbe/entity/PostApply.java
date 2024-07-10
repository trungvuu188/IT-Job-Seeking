package com.jobseeking.jobseekingbe.entity;

import com.jobseeking.jobseekingbe.entity.keys.KeyPostCandidate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "post_apply")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(KeyPostCandidate.class)
public class PostApply {

    @Id
    @Column(name = "user_id")
    String userId;

    @Id
    @Column(name = "post_id")
    int postId;
}
