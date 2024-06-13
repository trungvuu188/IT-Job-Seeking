package com.jobseeking.jobseekingbe.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity(name = "job_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostType {

    @Id
    @Column(name = "job_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int typeId;

    @Column(name = "job_type_title")
    String typeTitle;

    @OneToMany(mappedBy = "postType")
    Set<PostTypeDetail> postTypeDetails;
}
