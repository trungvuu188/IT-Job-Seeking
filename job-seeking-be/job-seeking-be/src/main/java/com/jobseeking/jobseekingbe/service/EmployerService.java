package com.jobseeking.jobseekingbe.service;

import com.jobseeking.jobseekingbe.entity.Employer;
import com.jobseeking.jobseekingbe.repository.EmployerRepository;
import com.jobseeking.jobseekingbe.service.imp.EmployerServiceImp;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EmployerService implements EmployerServiceImp {

    EmployerRepository employerRepository;

    @Override
    public Employer getEmployer(String email) {
        return null;
    }
}
