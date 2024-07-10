package com.jobseeking.jobseekingbe.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity(name = "requirement_desc")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "req_id")
    int reqId;

    @Column(name = "req_title")
    String reqTitle;

    @Column(name = "req_desc")
    String reqDesc;

    @ManyToOne
    @JoinColumn(name = "post_id")
    Post post;
}
