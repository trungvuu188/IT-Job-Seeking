package com.jobseeking.jobseekingbe.entity.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KeyPostLevel implements Serializable {

    @Column(name = "post_id")
    int postId;

    @Column(name = "level_id")
    int levelId;
}
