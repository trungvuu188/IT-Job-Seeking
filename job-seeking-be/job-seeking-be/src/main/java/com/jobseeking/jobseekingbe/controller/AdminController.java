package com.jobseeking.jobseekingbe.controller;

import com.jobseeking.jobseekingbe.dto.ApiResponse;
import com.jobseeking.jobseekingbe.dto.response.CandidateDTO;
import com.jobseeking.jobseekingbe.dto.response.CompanyDTO;
import com.jobseeking.jobseekingbe.dto.response.EmployerDTO;
import com.jobseeking.jobseekingbe.dto.response.PostSaveDTO;
import com.jobseeking.jobseekingbe.service.imp.AuthenticationServiceImp;
import com.jobseeking.jobseekingbe.service.imp.CandidateServiceImp;
import com.jobseeking.jobseekingbe.service.imp.EmployerServiceImp;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AdminController {

    CandidateServiceImp candidateServiceImp;
    EmployerServiceImp employerServiceImp;
    AuthenticationServiceImp authenticationServiceImp;

    @GetMapping("/candidate")
    public ApiResponse<List<CandidateDTO>> getAllCandidate() {
        return ApiResponse.<List<CandidateDTO>>builder()
                .result(candidateServiceImp.getAllCandidate())
                .build();
    }

    @GetMapping("/employer")
    public ApiResponse<List<CompanyDTO>> getAllEmployer() {
        return ApiResponse.<List<CompanyDTO>>builder()
                .result(employerServiceImp.getAllCompany())
                .build();
    }

    @PostMapping("/ban-user/{id}")
    public ApiResponse<Boolean> banUser(@PathVariable String id) {
        return ApiResponse.<Boolean>builder()
                .result(authenticationServiceImp.banUser(id))
                .build();
    }

    @PostMapping("/unban-user/{id}")
    public ApiResponse<Boolean> unBanUser(@PathVariable String id) {
        return ApiResponse.<Boolean>builder()
                .result(authenticationServiceImp.unBanUser(id))
                .build();
    }
}
