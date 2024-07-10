package com.jobseeking.jobseekingbe.repository;

import com.jobseeking.jobseekingbe.entity.CandidateApply;
import com.jobseeking.jobseekingbe.entity.CandidateSave;
import com.jobseeking.jobseekingbe.entity.keys.KeyEmployerCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateApplyRepository extends JpaRepository<CandidateApply, KeyEmployerCandidate> {
}
