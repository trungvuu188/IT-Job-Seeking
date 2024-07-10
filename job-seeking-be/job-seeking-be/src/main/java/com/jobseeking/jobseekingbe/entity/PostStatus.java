package com.jobseeking.jobseekingbe.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity(name = "post_status")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int statusId;

    @Column(name = "status_title")
    String statusTitle;

    @OneToMany(mappedBy = "postStatus")
    Set<Post> posts;
}
