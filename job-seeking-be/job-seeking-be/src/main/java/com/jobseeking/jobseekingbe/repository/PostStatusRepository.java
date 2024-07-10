package com.jobseeking.jobseekingbe.repository;

import com.jobseeking.jobseekingbe.entity.Post;
import com.jobseeking.jobseekingbe.entity.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PostStatusRepository extends JpaRepository<PostStatus, Integer> {
    PostStatus findByStatusTitle(String title);
}
