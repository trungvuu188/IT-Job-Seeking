package com.jobseeking.jobseekingbe.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "job_type")
public class JobType {

    @Id
    @Column(name = "job_type_id")
    private Integer jobTypeId;

    @Column(name = "job_type_name", length = 100)
    private String jobTypeName;

    // Getters and setters

    public Integer getJobTypeId() {
        return jobTypeId;
    }

    public void setJobTypeId(Integer jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    public String getJobTypeName() {
        return jobTypeName;
    }

    public void setJobTypeName(String jobTypeName) {
        this.jobTypeName = jobTypeName;
    }
}

