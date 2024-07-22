package com.jobseeking.jobseekingbe.repository;

import com.jobseeking.jobseekingbe.entity.PostApplyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostApplyStatusRepository extends JpaRepository<PostApplyStatus, Integer> {
    PostApplyStatus findByStatusTitle(String title);
}
