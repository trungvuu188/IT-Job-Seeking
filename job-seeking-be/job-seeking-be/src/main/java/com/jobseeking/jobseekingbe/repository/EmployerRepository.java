package com.jobseeking.jobseekingbe.repository;

import com.jobseeking.jobseekingbe.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, String> {
}
