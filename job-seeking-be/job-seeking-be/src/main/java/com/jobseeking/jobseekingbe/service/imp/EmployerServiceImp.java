package com.jobseeking.jobseekingbe.service.imp;

import com.jobseeking.jobseekingbe.dto.request.CandidateSaveRequest;
import com.jobseeking.jobseekingbe.dto.request.CompanySearchRequest;
import com.jobseeking.jobseekingbe.dto.request.EmployerUpdateRequest;
import com.jobseeking.jobseekingbe.dto.response.CandidateApplyDTO;
import com.jobseeking.jobseekingbe.dto.response.CompanyDTO;
import com.jobseeking.jobseekingbe.dto.response.EmployerDTO;
import com.jobseeking.jobseekingbe.entity.CandidateApply;
import com.jobseeking.jobseekingbe.entity.Employer;

import java.util.List;

public interface EmployerServiceImp {

    List<CompanyDTO> getAllCompany();
    Employer getEmployerById(String id);
    String saveEmployer(Employer employer);
    EmployerDTO getEmployerInfo(String id);
    boolean updateEmployerInfo(String id, EmployerUpdateRequest employerUpdateRequest);
    boolean acceptCandidate(String candidateId, int postId);
    boolean rejectCandidate(String candidateId, int postId);
    boolean saveCandidate(CandidateSaveRequest candidateSaveRequest);
    List<CandidateApplyDTO> getAllCandidateSaved(String userId);
    boolean deleteCandidateSaved(String empId, String candidateId);
    boolean deleteCandidateApply(String candidateId, int postId);
    List<CompanyDTO> filterCompany(CompanySearchRequest companySearchRequest);
    List<CandidateApplyDTO> getAllCandidateApply(String userId);
}
