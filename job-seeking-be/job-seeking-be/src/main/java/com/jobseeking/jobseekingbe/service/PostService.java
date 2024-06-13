package com.jobseeking.jobseekingbe.service;

import com.jobseeking.jobseekingbe.dto.request.PostCreationRequest;
import com.jobseeking.jobseekingbe.dto.response.PostDTO;
import com.jobseeking.jobseekingbe.entity.Employer;
import com.jobseeking.jobseekingbe.entity.Post;
import com.jobseeking.jobseekingbe.repository.EmployerRepository;
import com.jobseeking.jobseekingbe.repository.PostRepository;
import com.jobseeking.jobseekingbe.service.imp.PostServiceImp;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PostService implements PostServiceImp {

    PostRepository postRepository;
    EmployerRepository employerRepository;

    @Override
    public List<PostDTO> getAllPost() {
        List<PostDTO> posts = new ArrayList<>();
        for ( Post post : postRepository.findAll() ) {
            String salary = post.getMinSalary() + " - " + post.getMaxSalary();
            PostDTO postDTO = PostDTO.builder()
                    .title(post.getJobTitle())
                    .companyName(post.getEmployer().getCompanyName())
                    .expiredDate(post.getEndDate())
                    .location(post.getEmployer().getLocation())
                    .salary(salary)
                    .requirements(post.getPostRequirements())
                    .levels(post.getPostLevelDetails())
                    .types(post.getPostTypeDetails())
                    .contracts(post.getPostContractDetails())
                    .tech(post.getTechnologies())
                    .build();
            posts.add(postDTO);
        }
        return posts;
    }

    @Override
    public boolean addPost(PostCreationRequest postCreationRequest) {

//        Employer employer =
//        Post post = Post.builder()
//
//                .build();


        return false;
    }


}
