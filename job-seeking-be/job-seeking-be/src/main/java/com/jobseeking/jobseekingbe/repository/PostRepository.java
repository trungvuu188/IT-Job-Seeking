package com.jobseeking.jobseekingbe.repository;

import com.jobseeking.jobseekingbe.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
