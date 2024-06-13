package com.jobseeking.jobseekingbe.service.imp;

import com.jobseeking.jobseekingbe.dto.request.PostCreationRequest;
import com.jobseeking.jobseekingbe.dto.response.PostDTO;

import java.util.List;

public interface PostServiceImp {
    List<PostDTO> getAllPost();
    boolean addPost(PostCreationRequest postCreationRequest);
}
