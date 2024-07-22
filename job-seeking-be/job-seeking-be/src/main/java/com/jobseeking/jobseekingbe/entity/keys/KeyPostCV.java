package com.jobseeking.jobseekingbe.entity.keys;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KeyPostCV implements Serializable {

    @Column(name = "post_id")
    int postId;

    @Column(name = "cv_id")
    int cvId;
}
