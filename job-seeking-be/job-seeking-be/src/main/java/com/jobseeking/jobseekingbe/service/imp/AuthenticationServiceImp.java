package com.jobseeking.jobseekingbe.service.imp;

import com.jobseeking.jobseekingbe.dto.request.AuthenticationRequest;
import com.jobseeking.jobseekingbe.dto.request.ChangePasswordRequest;
import com.jobseeking.jobseekingbe.dto.request.IntrospectRequest;
import com.jobseeking.jobseekingbe.dto.request.UserCreationRequest;
import com.jobseeking.jobseekingbe.dto.response.AuthenticationResponse;
import com.jobseeking.jobseekingbe.dto.response.ChangePasswordResponse;
import com.jobseeking.jobseekingbe.dto.response.IntrospectResponse;
import com.jobseeking.jobseekingbe.entity.User;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationServiceImp {
    AuthenticationResponse accountRegister(UserCreationRequest userCreationRequest);
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
    String generateToken(String email);
    IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException;
    void resetPassword(String email);
    boolean changePassword(ChangePasswordRequest changePasswordRequest) throws ParseException, JOSEException;

    boolean updatePassword(String email, String newPassword);

    String getEmailFromToken(String token) throws JOSEException, ParseException;

}
