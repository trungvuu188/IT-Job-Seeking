package com.jobseeking.jobseekingbe.service;

import com.jobseeking.jobseekingbe.dto.request.UserCreationRequest;
import com.jobseeking.jobseekingbe.dto.response.UserDTO;
import com.jobseeking.jobseekingbe.entity.Role;
import com.jobseeking.jobseekingbe.entity.User;
import com.jobseeking.jobseekingbe.repository.RoleRepository;
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
    RoleRepository roleRepository;

    public User accountRegister(UserCreationRequest userCreationRequest) {

        if(userRepository.existsByEmail(userCreationRequest.getEmail())) {
            throw new RuntimeException("Email existed");
        }

        Role role = roleRepository.findById(userCreationRequest.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role Id not found"));

        User user = User.builder()
                .email(userCreationRequest.getEmail())
                .password(userCreationRequest.getPassword())
                .role(role)
                .build();

        return userRepository.save(user);
    }
}
