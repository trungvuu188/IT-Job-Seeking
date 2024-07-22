package com.jobseeking.jobseekingbe.repository;

import com.jobseeking.jobseekingbe.entity.CandidateSave;
import com.jobseeking.jobseekingbe.entity.keys.KeyEmployerCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateSaveRepository extends JpaRepository<CandidateSave, KeyEmployerCandidate> {

    List<CandidateSave> findAllByKeyEmployerCandidateEmployerId(String employerId);
    void deleteByKeyEmployerCandidateEmployerIdAndKeyEmployerCandidateCandidateId(String employerId, String candidateId);
}
