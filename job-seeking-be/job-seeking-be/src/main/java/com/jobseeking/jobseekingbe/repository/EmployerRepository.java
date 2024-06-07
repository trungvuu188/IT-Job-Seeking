package com.jobseeking.jobseekingbe.repository;

import com.jobseeking.jobseekingbe.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends JpaRepository<Company, Integer> {

}
