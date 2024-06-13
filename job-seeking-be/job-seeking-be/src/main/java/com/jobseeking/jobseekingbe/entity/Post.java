package com.jobseeking.jobseekingbe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Entity(name = "post")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int postId;

    @Column(name = "job_title")
    String jobTitle;

    @Column(name = "status")
    String status;

    @Column(name = "min_salary")
    Long minSalary;

    @Column(name = "max_salary")
    Long maxSalary;

    @Column(name = "end_date")
    Date endDate;

    @Column(name = "tech")
    String technologies;

    @Column(name = "welfare")
    String welfare;

    @ManyToOne
    @JoinColumn(name = "user_id")
//    @MapsId
    Employer employer;

    @OneToMany(mappedBy = "post")
    Set<PostRequirement> postRequirements;

    @OneToMany(mappedBy = "post")
    Set<PostLevelDetail> postLevelDetails;

    @OneToMany(mappedBy = "post")
    Set<PostTypeDetail> postTypeDetails;

    @OneToMany(mappedBy = "post")
    Set<PostContractDetail> postContractDetails;
}
