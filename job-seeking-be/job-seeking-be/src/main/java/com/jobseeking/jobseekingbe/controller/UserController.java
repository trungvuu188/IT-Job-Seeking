package com.jobseeking.jobseekingbe.controller;

import com.jobseeking.jobseekingbe.dto.ApiResponse;
import com.jobseeking.jobseekingbe.dto.request.ChangePasswordRequest;
import com.jobseeking.jobseekingbe.dto.request.IntrospectRequest;
import com.jobseeking.jobseekingbe.dto.request.ResetPasswordRequest;
import com.jobseeking.jobseekingbe.dto.request.UserCreationRequest;
import com.jobseeking.jobseekingbe.dto.response.AuthenticationResponse;
import com.jobseeking.jobseekingbe.dto.response.UserDTO;
import com.jobseeking.jobseekingbe.entity.Employer;
import com.jobseeking.jobseekingbe.entity.User;
import com.jobseeking.jobseekingbe.service.imp.AuthenticationServiceImp;
import com.jobseeking.jobseekingbe.service.imp.EmployerServiceImp;
import com.jobseeking.jobseekingbe.service.imp.UserServiceImp;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@CrossOrigin
@RestController
@RequestMapping("/user")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserController {

    UserServiceImp userServiceImp;
    AuthenticationServiceImp authenticationServiceImp;

    @PostMapping()
    public ApiResponse<AuthenticationResponse> accountRegister(@RequestBody UserCreationRequest userCreationRequest) {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationServiceImp.accountRegister(userCreationRequest))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserDTO> getUserById(@PathVariable String id) {
        var result = userServiceImp.getUserById(id);
        return ApiResponse.<UserDTO>builder()
                .result(result)
                .build();
    }

    EmployerServiceImp employerServiceImp;
    @PostMapping("/emp")
    public ApiResponse<Employer> getEmp(@RequestBody ResetPasswordRequest email ) {
        var result = employerServiceImp.getEmployer(email.getEmail());
        return ApiResponse.<Employer>builder()
                .result(result)
                .build();
    }

//    @PutMapping("/update")
//    public ApiResponse<String> updateUser


}
