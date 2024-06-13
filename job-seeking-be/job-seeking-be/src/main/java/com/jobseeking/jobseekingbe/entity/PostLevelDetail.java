package com.jobseeking.jobseekingbe.entity;

import com.jobseeking.jobseekingbe.entity.keys.KeyPostLevel;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity(name = "post_job_level")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostLevelDetail {

    @EmbeddedId
    KeyPostLevel keyPostLevel;

    @ManyToOne
    @JoinColumn(name = "post_id", insertable = false, updatable = false)
    Post post;

    @ManyToOne
    @JoinColumn(name = "level_id", insertable = false, updatable = false)
    PostLevel postLevel;
}
