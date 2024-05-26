package com.jobseeking.jobseekingbe.service.imp;

import com.jobseeking.jobseekingbe.dto.request.AuthenticationRequest;
import com.jobseeking.jobseekingbe.dto.response.AuthenticationResponse;

public interface AuthenticationServiceImp {
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}
