package com.jobseeking.jobseekingbe.service.imp;

import com.jobseeking.jobseekingbe.dto.request.ApplyCVRequest;
import com.jobseeking.jobseekingbe.dto.request.CandidateUpdateRequest;
import com.jobseeking.jobseekingbe.dto.request.FileUploadRequest;
import com.jobseeking.jobseekingbe.dto.response.*;
import com.jobseeking.jobseekingbe.entity.Candidate;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface CandidateServiceImp {

    List<CandidateDTO> getAllCandidate();
    Candidate getCandidateById(String id);
    CandidateDTO getCandidateInfo(String id);
    boolean updateCandidateInfo(String id, CandidateUpdateRequest candidateUpdateRequest) throws ParseException;
    boolean savePost(String userId, int postId);
    boolean deletePostSave(String userId, int postId);
    List<PostSaveDTO> getAllPostSave(String userId);
    boolean followCompany(String userId, String companyId);
    boolean unFollowCompany(String userId, String companyId);
    List<CompanyDTO> getFollowCompanies(String userId);
    boolean uploadCV(FileUploadRequest fileUploadRequest) throws IOException;
    List<CVDTO> getCVs(String userId);
    boolean deleteCV(String userId, int fileId);
    boolean applyCV(ApplyCVRequest applyCVRequest);
    List<PostApplyDTO> getAllPostApply(String userId);
    boolean deletePostApply(String userId, int postId);
}
