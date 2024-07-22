package com.jobseeking.jobseekingbe.repository;

import com.jobseeking.jobseekingbe.entity.CandidateApply;
import com.jobseeking.jobseekingbe.entity.CandidateSave;
import com.jobseeking.jobseekingbe.entity.keys.KeyEmployerCandidate;
import com.jobseeking.jobseekingbe.entity.keys.KeyPostCV;
import com.jobseeking.jobseekingbe.entity.keys.KeyPostCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateApplyRepository extends JpaRepository<CandidateApply, KeyPostCandidate> {
    List<CandidateApply> findAllByKeyPostCandidatePostId(int postId);

    CandidateApply findByKeyPostCandidateCandidateIdAndKeyPostCandidatePostId(String candidateId, int postId);

    void deleteByKeyPostCandidateCandidateIdAndKeyPostCandidatePostId(String candidateId, int postId);

}
