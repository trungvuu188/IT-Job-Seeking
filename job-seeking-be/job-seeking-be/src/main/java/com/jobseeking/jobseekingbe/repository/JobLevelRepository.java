package com.jobseeking.jobseekingbe.repository;

import com.jobseeking.jobseekingbe.entity.PostLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobLevelRepository extends JpaRepository<PostLevel, Integer> {
}
