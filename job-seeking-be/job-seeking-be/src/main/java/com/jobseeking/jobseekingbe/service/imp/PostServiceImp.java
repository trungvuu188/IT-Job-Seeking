package com.jobseeking.jobseekingbe.service.imp;

import com.jobseeking.jobseekingbe.dto.ApiResponse;
import com.jobseeking.jobseekingbe.dto.request.PostCreationRequest;
import com.jobseeking.jobseekingbe.dto.response.*;
import com.jobseeking.jobseekingbe.entity.Employer;
import com.jobseeking.jobseekingbe.entity.Post;

import java.util.List;

public interface PostServiceImp {

    int savePost(Post post);
    List<PostDTO> getAllPost();
    PostDTO getPostById(int id);
    List<PostDTO> getAllPendingPosts();

    boolean activePost(int postId);
    boolean rejectPost(int postId);

    List<PostDTO> getAllPostByEmployerId(String id);
    AuthenticationResponse addPost(PostCreationRequest postCreationRequest);

    boolean addPostWhenAuthenticated(String id, PostCreationRequest postCreationRequest);

    void insertPost(Employer employer, PostCreationRequest postCreationRequest);

    boolean updatePost(int postId, PostCreationRequest postCreationRequest);

    boolean updatePostLevelDetail(int postId, String [] listLevelId);
    boolean updatePostTypeDetail(int postId, String [] listTypeId);
    boolean updatePostContractDetail(int postId, String [] listContractId);

    void updatePostRequirement(int postId, String title, String [] requirement);

    List<PostLevelDTO> getPostLevels();
    List<PostTypeDTO> getPostTypes();
    List<PostContractDTO> getPostContracts();

    PostRequirementDTO getPostRequirement(int postId);

    PostDTO postMapper(Post post);
}
