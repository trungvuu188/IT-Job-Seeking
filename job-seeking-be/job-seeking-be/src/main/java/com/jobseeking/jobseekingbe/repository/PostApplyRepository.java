package com.jobseeking.jobseekingbe.repository;

import com.jobseeking.jobseekingbe.entity.PostApply;
import com.jobseeking.jobseekingbe.entity.keys.KeyPostCV;
import com.jobseeking.jobseekingbe.entity.keys.KeyPostCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostApplyRepository extends JpaRepository<PostApply, KeyPostCandidate> {

    PostApply findByKeyPostCandidateCandidateIdAndAndKeyPostCandidatePostId(String candidateId, int postId);
    List<PostApply> findAllByKeyPostCandidateCandidateId(String userId);

    List<PostApply> findAllByKeyPostCandidatePostId(int postId);
    void deleteByKeyPostCandidateCandidateIdAndKeyPostCandidatePostId(String userId, int postId);
}
