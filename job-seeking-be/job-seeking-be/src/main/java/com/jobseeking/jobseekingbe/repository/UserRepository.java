package com.jobseeking.jobseekingbe.repository;

import com.jobseeking.jobseekingbe.entity.Employer;
import com.jobseeking.jobseekingbe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
    User getUserByEmail(String email);
    User getUserById(String id);
    boolean existsById(String id);

}
