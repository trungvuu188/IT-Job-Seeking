package com.jobseeking.jobseekingbe.service.imp;

import com.jobseeking.jobseekingbe.dto.request.UserCreationRequest;
import com.jobseeking.jobseekingbe.entity.User;

public interface UserServiceImp {

    User accountRegister(UserCreationRequest userCreationRequest);
}
