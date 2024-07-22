package com.jobseeking.jobseekingbe.controller;

import com.jobseeking.jobseekingbe.dto.ApiResponse;
import com.jobseeking.jobseekingbe.dto.request.CandidateSaveRequest;
import com.jobseeking.jobseekingbe.dto.request.CompanySearchRequest;
import com.jobseeking.jobseekingbe.dto.request.PostCreationRequest;
import com.jobseeking.jobseekingbe.dto.response.CandidateApplyDTO;
import com.jobseeking.jobseekingbe.dto.response.CompanyDTO;
import com.jobseeking.jobseekingbe.dto.response.PostDTO;
import com.jobseeking.jobseekingbe.service.imp.EmployerServiceImp;
import com.jobseeking.jobseekingbe.service.imp.PostServiceImp;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/employer")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EmployerController {

    EmployerServiceImp employerServiceImp;
    @PostMapping("/filter")
    public ApiResponse<List<CompanyDTO>> filterCompany(@RequestBody CompanySearchRequest companySearchRequest) {
        return ApiResponse.<List<CompanyDTO>>builder()
                .result(employerServiceImp.filterCompany(companySearchRequest))
                .build();
    }

    @GetMapping("/candidate-apply/{id}")
    public ApiResponse<List<CandidateApplyDTO>> getAllCandidateApply(@PathVariable String id) {
        return ApiResponse.<List<CandidateApplyDTO>>builder()
                .result(employerServiceImp.getAllCandidateApply(id))
                .build();
    }

    @PostMapping("/candidate-apply/accept/{userId}&{postId}")
    public ApiResponse<Boolean> acceptCandidate(@PathVariable String userId,
                                                         @PathVariable int postId) {
        return ApiResponse.<Boolean>builder()
                .result(employerServiceImp.acceptCandidate(userId, postId))
                .build();
    }

    @PostMapping("/candidate-apply/reject/{userId}&{postId}")
    public ApiResponse<Boolean> rejectCandidate(@PathVariable String userId,
                                                @PathVariable int postId) {
        return ApiResponse.<Boolean>builder()
                .result(employerServiceImp.rejectCandidate( userId, postId))
                .build();
    }

    @DeleteMapping("/candidate-apply/delete/{userId}&{postId}")
    public ApiResponse<Boolean> deleteCandidate(@PathVariable String userId,
                                                @PathVariable int postId) {
        return ApiResponse.<Boolean>builder()
                .result(employerServiceImp.deleteCandidateApply( userId, postId))
                .build();
    }

    @GetMapping("/candidate-apply/save/{id}")
    public ApiResponse<List<CandidateApplyDTO>> getAllCandidateSaved(@PathVariable String id) {
        return ApiResponse.<List<CandidateApplyDTO>>builder()
                .result(employerServiceImp.getAllCandidateSaved(id))
                .build();
    }
    @PostMapping("/candidate-apply/save")
    public ApiResponse<Boolean> saveCandidate(@RequestBody CandidateSaveRequest candidateSaveRequest) {
        return ApiResponse.<Boolean>builder()
                .result(employerServiceImp.saveCandidate(candidateSaveRequest))
                .build();
    }

    @DeleteMapping("/candidate-apply/save")
    public ApiResponse<Boolean> deleteCandidateSaved(@RequestBody CandidateSaveRequest candidateSaveRequest) {
        return ApiResponse.<Boolean>builder()
                .result(employerServiceImp.deleteCandidateSaved(candidateSaveRequest.getEmployerId(), candidateSaveRequest.getCandidateId()))
                .build();
    }

}
