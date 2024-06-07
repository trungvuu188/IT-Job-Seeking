package com.jobseeking.jobseekingbe.controller;

import com.jobseeking.jobseekingbe.dto.ApiResponse;
import com.jobseeking.jobseekingbe.dto.request.AuthenticationRequest;
import com.jobseeking.jobseekingbe.dto.response.AuthenticationResponse;
import com.jobseeking.jobseekingbe.service.imp.AuthenticationServiceImp;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthenticationController {

    AuthenticationServiceImp authenticationServiceImp;

    @PostMapping("/login")
    ResponseEntity<ApiResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {

        ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(authenticationServiceImp.login(authenticationRequest));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }



}
