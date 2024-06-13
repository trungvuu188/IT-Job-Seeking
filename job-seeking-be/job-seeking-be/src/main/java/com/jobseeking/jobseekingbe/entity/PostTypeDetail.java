package com.jobseeking.jobseekingbe.entity;

import com.jobseeking.jobseekingbe.entity.keys.KeyPostLevel;
import com.jobseeking.jobseekingbe.entity.keys.KeyPostType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity(name = "post_job_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostTypeDetail {
    @EmbeddedId
    KeyPostType keyPostType;

    @ManyToOne
    @JoinColumn(name = "post_id", insertable = false, updatable = false)
    Post post;

    @ManyToOne
    @JoinColumn(name = "job_type_id", insertable = false, updatable = false)
    PostType postType;
}
