package com.jobseeking.jobseekingbe.repository;

import com.jobseeking.jobseekingbe.entity.PostSave;
import com.jobseeking.jobseekingbe.entity.keys.KeyPostCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostSaveRepository extends JpaRepository<PostSave, KeyPostCandidate> {
}
