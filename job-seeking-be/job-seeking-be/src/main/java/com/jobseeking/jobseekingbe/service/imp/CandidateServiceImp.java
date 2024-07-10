package com.jobseeking.jobseekingbe.service.imp;

import com.jobseeking.jobseekingbe.dto.request.AvatarUpdateRequest;
import com.jobseeking.jobseekingbe.dto.request.CandidateUpdateRequest;
import com.jobseeking.jobseekingbe.dto.request.EmployerUpdateRequest;
import com.jobseeking.jobseekingbe.dto.response.CandidateDTO;
import com.jobseeking.jobseekingbe.entity.Candidate;
import com.jobseeking.jobseekingbe.entity.Employer;

import java.text.ParseException;

public interface CandidateServiceImp {
    Candidate getCandidateById(String id);
    CandidateDTO getCandidateInfo(String id);
    boolean updateCandidateInfo(String id, CandidateUpdateRequest candidateUpdateRequest) throws ParseException;

}
