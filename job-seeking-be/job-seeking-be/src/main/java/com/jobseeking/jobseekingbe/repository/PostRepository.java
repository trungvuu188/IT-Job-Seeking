package com.jobseeking.jobseekingbe.repository;

import com.jobseeking.jobseekingbe.entity.Post;
import com.jobseeking.jobseekingbe.entity.PostRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> getAllByEmployerId(String userId);
    List<Post> findAllByPostStatusStatusTitle(String title);
}
