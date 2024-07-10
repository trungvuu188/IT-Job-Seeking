package com.jobseeking.jobseekingbe.service.imp;

import com.jobseeking.jobseekingbe.dto.request.EmployerUpdateRequest;
import com.jobseeking.jobseekingbe.dto.response.EmployerDTO;
import com.jobseeking.jobseekingbe.entity.Employer;

public interface EmployerServiceImp {
    Employer getEmployerById(String id);
    String saveEmployer(Employer employer);
    EmployerDTO getEmployerInfo(String id);
    boolean updateEmployerInfo(String id, EmployerUpdateRequest employerUpdateRequest);
}
