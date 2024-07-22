package com.jobseeking.jobseekingbe.repository;

import com.jobseeking.jobseekingbe.entity.CV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CVRepository extends JpaRepository<CV, Integer> {
    List<CV> getAllByCandidateId(String candidateId);
    void deleteByCandidateIdAndCvId(String candidateId, int cvId);

}
