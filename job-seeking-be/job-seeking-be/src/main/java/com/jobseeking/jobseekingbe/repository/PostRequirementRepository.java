package com.jobseeking.jobseekingbe.repository;

import com.jobseeking.jobseekingbe.entity.PostRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PostRequirementRepository extends JpaRepository<PostRequirement, Integer> {
    Set<PostRequirement> findAllByPostPostId(int postId);

    void deleteAllByPostPostId(int postId);
}
