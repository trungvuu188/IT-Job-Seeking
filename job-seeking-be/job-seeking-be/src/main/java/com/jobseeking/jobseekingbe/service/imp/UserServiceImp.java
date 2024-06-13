package com.jobseeking.jobseekingbe.service.imp;

import com.jobseeking.jobseekingbe.dto.request.UserCreationRequest;
import com.jobseeking.jobseekingbe.dto.response.UserDTO;
import com.jobseeking.jobseekingbe.entity.User;


public interface UserServiceImp {

    UserDTO getUserById(String id);
    User getUserByEmail(String email);

    boolean updateUser(UserCreationRequest userCreationRequest);
}
