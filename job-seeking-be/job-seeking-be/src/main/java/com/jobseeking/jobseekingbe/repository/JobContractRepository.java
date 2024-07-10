package com.jobseeking.jobseekingbe.repository;

import com.jobseeking.jobseekingbe.entity.PostContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobContractRepository extends JpaRepository<PostContract, Integer> {
}
