package com.jobseeking.jobseekingbe.service;

import com.jobseeking.jobseekingbe.dto.response.UserDTO;
import com.jobseeking.jobseekingbe.entity.User;
import com.jobseeking.jobseekingbe.repository.UserRepository;
import com.jobseeking.jobseekingbe.service.imp.UserServiceImp;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserService implements UserServiceImp {

    UserRepository userRepository;

    @Override
    public UserDTO getUserById(String id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Id is not found");
        }

        User user = userRepository.getUserById(id);
        UserDTO userDTO = UserDTO.builder()
                .email(user.getEmail())
                .phone(user.getPhone())
                .roleName(user.getRole().getRoleName())
                .build();
        return userDTO;
    }



}
