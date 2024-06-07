package com.jobseeking.jobseekingbe.repository;

import com.jobseeking.jobseekingbe.entity.JobType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTypeRepository extends JpaRepository<JobType,Integer> {
}
