package com.jobseeking.jobseekingbe.controller;

import com.jobseeking.jobseekingbe.dto.ApiResponse;
import com.jobseeking.jobseekingbe.dto.request.PostCreationRequest;
import com.jobseeking.jobseekingbe.dto.response.PostDTO;
import com.jobseeking.jobseekingbe.service.imp.PostServiceImp;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.var;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/post")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PostController {

    PostServiceImp postServiceImp;

    @GetMapping()
    public ApiResponse<List<PostDTO>> getAllPost() {
        return ApiResponse.<List<PostDTO>>builder()
                .result(postServiceImp.getAllPost())
                .build();
    }

    @PostMapping()
    public ApiResponse<Boolean> addPost(@RequestBody PostCreationRequest postCreationRequest) {
        return ApiResponse.<Boolean>builder()
                .result(postServiceImp.addPost(postCreationRequest))
                .build();
    }


}
