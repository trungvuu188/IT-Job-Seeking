package com.jobseeking.jobseekingbe.repository;

import com.jobseeking.jobseekingbe.entity.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, String> {
}
