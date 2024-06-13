package com.jobseeking.jobseekingbe.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity(name = "job_level")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostLevel {

    @Id
    @Column(name = "level_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int levelId;

    @Column(name = "level_title")
    String levelTitle;

    @OneToMany(mappedBy = "postLevel")
    Set<PostLevelDetail> postLevelDetails;
}
