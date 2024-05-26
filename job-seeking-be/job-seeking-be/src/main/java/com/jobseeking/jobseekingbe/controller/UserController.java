package com.jobseeking.jobseekingbe.controller;

import com.jobseeking.jobseekingbe.dto.ApiResponse;
import com.jobseeking.jobseekingbe.dto.request.UserCreationRequest;
import com.jobseeking.jobseekingbe.dto.response.UserDTO;
import com.jobseeking.jobseekingbe.entity.User;
import com.jobseeking.jobseekingbe.service.imp.UserServiceImp;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserController {

    UserServiceImp userServiceImp;

    @PostMapping()
    public ResponseEntity<ApiResponse> accountRegister(@RequestBody UserCreationRequest userCreationRequest) {

        User user = userServiceImp.accountRegister(userCreationRequest);
        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .roleName(user.getRole().getRoleName())
                .build();

        ApiResponse<UserDTO> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userDTO);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
