package com.jobseeking.jobseekingbe.repository;

import com.jobseeking.jobseekingbe.entity.PostType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTypeRepository extends JpaRepository<PostType, Integer> {
}
