package com.jobseeking.jobseekingbe.service;

import com.jobseeking.jobseekingbe.dto.ApiResponse;
import com.jobseeking.jobseekingbe.dto.request.PostCreationRequest;
import com.jobseeking.jobseekingbe.dto.response.*;
import com.jobseeking.jobseekingbe.entity.*;
import com.jobseeking.jobseekingbe.entity.keys.KeyPostContract;
import com.jobseeking.jobseekingbe.entity.keys.KeyPostLevel;
import com.jobseeking.jobseekingbe.entity.keys.KeyPostType;
import com.jobseeking.jobseekingbe.repository.*;
import com.jobseeking.jobseekingbe.service.imp.AuthenticationServiceImp;
import com.jobseeking.jobseekingbe.service.imp.EmployerServiceImp;
import com.jobseeking.jobseekingbe.service.imp.PostServiceImp;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PostService implements PostServiceImp {

    PostRepository postRepository;
    UserRepository userRepository;
    EmployerRepository employerRepository;
    JobTypeRepository jobTypeRepository;
    JobLevelRepository jobLevelRepository;
    JobContractRepository jobContractRepository;
    RoleRepository roleRepository;
    EmployerServiceImp employerServiceImp;
    PostLevelDetailRepository postLevelDetailRepository;
    PostTypeDetailRepository postTypeDetailRepository;
    PostContractDetailRepository postContractDetailRepository;
    AuthenticationServiceImp authenticationServiceImp;
    PostRequirementRepository postRequirementRepository;
    PostStatusRepository postStatusRepository;
    String POST_REQUIREMENT_ROLE = "ROLE";
    String POST_REQUIREMENT_SKILL = "SKILL";
    String POST_REQUIREMENT_BENEFIT = "BENEFIT";
    String POST_REQUIREMENT_PROGRESS = "PROGRESS";
    String POST_REQUIREMENT_MINIMUM = "MINIMUM";

    @Override
    public int savePost(Post post) {
        postRepository.save(post);
        return post.getPostId();
    }

    @Override
    public List<PostDTO> getAllPost() {
        List<PostDTO> posts = new ArrayList<>();
        for ( Post post : postRepository.findAll() ) {
            if (post.getPostStatus().getStatusTitle().equalsIgnoreCase("ACTIVE")) {
                PostDTO postDTO = getPostById(post.getPostId());
                posts.add(postDTO);
            }
        }
        return posts;
    }

    @Override
    public PostDTO getPostById(int id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post is not found"));

        return postMapper(post);
    }

    @Override
    public List<PostDTO> getAllPendingPosts() {
        List<PostDTO> posts = new ArrayList<>();
        for ( Post post : postRepository.findAllByPostStatusStatusTitle("PENDING") ) {
            PostDTO postDTO = postMapper(post);
            posts.add(postDTO);
        }
        return posts;
    }

    @Override
    public boolean activePost(int postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post is not found"));

        PostStatus postStatus = postStatusRepository.findByStatusTitle("ACTIVE");
        post.setPostStatus(postStatus);
        postRepository.save(post);
        return true;
    }

    @Override
    public boolean rejectPost(int postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post is not found"));

        PostStatus postStatus = postStatusRepository.findByStatusTitle("REJECTED");
        post.setPostStatus(postStatus);
        postRepository.save(post);
        return true;
    }

    @Override
    public List<PostDTO> getAllPostByEmployerId(String id) {
        List<Post> posts = postRepository.getAllByEmployerId(id);
        List<PostDTO> postDTOS = new ArrayList<>();
        for( Post p : posts ) {
            PostDTO postDTO = getPostById(p.getPostId());
            postDTOS.add(postDTO);
        }
        return postDTOS;
    }

    @Override
    public AuthenticationResponse addPost(PostCreationRequest postCreationRequest) {

        String userId = null;
        String userRole = null;
        String token = null;

        if(!userRepository.existsByEmail(postCreationRequest.getEmail())) {
            final int EMPLOYER_ROLE_ID = 3;
            Role role = roleRepository.findById(EMPLOYER_ROLE_ID)
                    .orElseThrow(() -> new RuntimeException("Role Id not found"));
            Employer employer = Employer.builder()
                    .email(postCreationRequest.getEmail())
                    .password(UUID.randomUUID().toString())
                    .role(role)
                    .companyName(postCreationRequest.getCompanyName())
                    .build();

            userId = employerServiceImp.saveEmployer(employer);
            userRole = role.getRoleName();
            token = authenticationServiceImp.generateToken(employer.getEmail());

            insertPost(employer, postCreationRequest);

        } else {

            User user = userRepository.getUserByEmail(postCreationRequest.getEmail());

            if (user.getRole().getRoleName().equals("CANDIDATE")) {
                userRole = user.getRole().getRoleName();
            } else {
                Employer employer = employerRepository.findEmployerById(user.getId());
                userRole = user.getRole().getRoleName();
                insertPost(employer, postCreationRequest);
            }
        }

        return AuthenticationResponse.builder()
                .userId(userId)
                .userRole(userRole)
                .token(token)
                .build();
    }

    @Override
    public boolean addPostWhenAuthenticated(String id, PostCreationRequest postCreationRequest) {

        Employer employer = employerRepository.findEmployerById(id);
        insertPost(employer, postCreationRequest);

        return true;
    }

    @Override
    public void insertPost(Employer employer, PostCreationRequest postCreationRequest) {

        PostStatus postStatus = postStatusRepository.findByStatusTitle("PENDING");

        Post post = Post.builder()
                .employer(employer)
                .jobTitle(postCreationRequest.getPosition())
                .jobDesc(postCreationRequest.getJobDetail())
                .minSalary(postCreationRequest.getMinSalary())
                .maxSalary(postCreationRequest.getMaxSalary())
                .endDate(postCreationRequest.getEndDate())
                .technologies(postCreationRequest.getTech())
                .postStatus(postStatus)
                .build();
        int postId = savePost(post);

        updatePostLevelDetail(postId, postCreationRequest.getLevel());
        updatePostTypeDetail(postId, postCreationRequest.getJobType());
        updatePostContractDetail(postId, postCreationRequest.getJobContract());
        updatePostRequirement(postId, POST_REQUIREMENT_ROLE, postCreationRequest.getRole());
        updatePostRequirement(postId, POST_REQUIREMENT_SKILL, postCreationRequest.getSkill());
        updatePostRequirement(postId, POST_REQUIREMENT_BENEFIT, postCreationRequest.getBenefit());
        updatePostRequirement(postId, POST_REQUIREMENT_PROGRESS, postCreationRequest.getProgress());
        updatePostRequirement(postId, POST_REQUIREMENT_MINIMUM, postCreationRequest.getMinimumYear());
    }

    @Override
    public boolean updatePost(int postId, PostCreationRequest postCreationRequest) {
        return false;
    }

    @Override
    public boolean updatePostLevelDetail(int postId, String [] listLevelId) {

            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new RuntimeException("Post is not found"));

            for(String i : listLevelId) {

                PostLevel postLevel = jobLevelRepository.findById(Integer.parseInt(i))
                        .orElseThrow(() -> new RuntimeException("Job Level id is not found"));

                KeyPostLevel keyPostLevel = KeyPostLevel.builder()
                        .postId(postId)
                        .levelId(postLevel.getLevelId())
                        .build();

                PostLevelDetail postLevelDetail = PostLevelDetail.builder()
                        .keyPostLevel(keyPostLevel)
                        .post(post)
                        .postLevel(postLevel)
                        .build();

                postLevelDetailRepository.save(postLevelDetail);
            }

            return true;
    }

    @Override
    public boolean updatePostTypeDetail(int postId, String[] listTypeId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post is not found"));

        for(String i : listTypeId) {
            PostType postType = jobTypeRepository.findById(Integer.parseInt(i))
                    .orElseThrow(() -> new RuntimeException("Job Type id is not found"));

            KeyPostType keyPostType = KeyPostType.builder()
                    .postId(postId)
                    .typeId(postType.getTypeId())
                    .build();

            PostTypeDetail postTypeDetail = PostTypeDetail.builder()
                    .keyPostType(keyPostType)
                    .post(post)
                    .postType(postType)
                    .build();

            postTypeDetailRepository.save(postTypeDetail);
        }
        return true;
    }

    @Override
    public boolean updatePostContractDetail(int postId, String[] listContractId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post is not found"));

        for(String i : listContractId) {
            PostContract postContract = jobContractRepository.findById(Integer.parseInt(i))
                    .orElseThrow(() -> new RuntimeException("Job Contract id is not found"));

            KeyPostContract keyPostContract = KeyPostContract.builder()
                    .postId(postId)
                    .contractId(postContract.getContractId())
                    .build();
            PostContractDetail postContractDetail = PostContractDetail.builder()
                    .keyPostContract(keyPostContract)
                    .post(post)
                    .postContract(postContract)
                    .build();

            postContractDetailRepository.save(postContractDetail);
        }
        return true;
    }

    @Override
    public void updatePostRequirement(int postId, String title, String[] requirement) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post is not found"));
        String data = String.join(";", requirement);
        Set<PostRequirement> postRequirements = postRequirementRepository.findAllByPostPostId(postId);

        if( postRequirements.size() >= 1 ) {
            boolean isExisted = false;
            for ( PostRequirement p : postRequirements ) {
                if(p.getReqTitle().equalsIgnoreCase(title)) {
                    isExisted = true;
                    p.setReqDesc(data);
                    postRequirementRepository.save(p);
                }
            }
            if (isExisted == false) {
                PostRequirement postRequirement = PostRequirement.builder()
                        .post(post)
                        .reqTitle(title)
                        .reqDesc(data)
                        .build();
                postRequirementRepository.save(postRequirement);
                postRequirements.add(postRequirement);
            }
        } else {
            PostRequirement postRequirement = PostRequirement.builder()
                    .post(post)
                    .reqTitle(title)
                    .reqDesc(data)
                    .build();
            postRequirementRepository.save(postRequirement);
            postRequirements.add(postRequirement);
        }
    }

    @Override
    public List<PostLevelDTO> getPostLevels() {
        List<PostLevel> postLevels = jobLevelRepository.findAll();
        List<PostLevelDTO> postLevelDTOS = new ArrayList<>();
        for(PostLevel p : postLevels) {
            PostLevelDTO postLevelDTO = PostLevelDTO.builder()
                    .id(p.getLevelId())
                    .value(p.getLevelTitle())
                    .build();
            postLevelDTOS.add(postLevelDTO);
        }
        return postLevelDTOS;
    }

    @Override
    public List<PostTypeDTO> getPostTypes() {
        List<PostType> postTypes = jobTypeRepository.findAll();
        List<PostTypeDTO> postTypeDTOS = new ArrayList<>();
        for(PostType p : postTypes) {
            PostTypeDTO postTypeDTO = PostTypeDTO.builder()
                    .id(p.getTypeId())
                    .value(p.getTypeTitle())
                    .build();
            postTypeDTOS.add(postTypeDTO);
        }
        return postTypeDTOS;
    }

    @Override
    public List<PostContractDTO> getPostContracts() {
        List<PostContract> postContracts = jobContractRepository.findAll();
        List<PostContractDTO> postContractDTOS = new ArrayList<>();
        for(PostContract p : postContracts) {
            PostContractDTO postContractDTO = PostContractDTO.builder()
                    .id(p.getContractId())
                    .value(p.getContractTitle())
                    .build();
            postContractDTOS.add(postContractDTO);
        }
        return postContractDTOS;
    }

    @Override
    public PostRequirementDTO getPostRequirement(int postId) {
        Set<PostRequirement> postRequirements = postRequirementRepository.findAllByPostPostId(postId);
        String [] role = {};
        String [] skill = {};
        String [] benefit = {};
        String [] progress = {};
        String [] minimum = {};

        for (PostRequirement p : postRequirements ) {
            if(p.getReqTitle().equalsIgnoreCase(POST_REQUIREMENT_ROLE)) {
                role = p.getReqDesc().split(";");
            } else if (p.getReqTitle().equalsIgnoreCase(POST_REQUIREMENT_SKILL)) {
                skill = p.getReqDesc().split(";");
            } else if (p.getReqTitle().equalsIgnoreCase(POST_REQUIREMENT_BENEFIT)) {
                benefit = p.getReqDesc().split(";");
            } else if (p.getReqTitle().equalsIgnoreCase(POST_REQUIREMENT_PROGRESS)) {
                progress = p.getReqDesc().split(";");
            } else if (p.getReqTitle().equalsIgnoreCase(POST_REQUIREMENT_MINIMUM)) {
                minimum = p.getReqDesc().split(";");
            }
        }

        return PostRequirementDTO.builder()
                .role(role)
                .skill(skill)
                .benefit(benefit)
                .progress(progress)
                .minimum(minimum)
                .build();
    }

    @Override
    public PostDTO postMapper(Post post) {
        byte[] postImageBytes = null;
        byte[] backgroundBytes = null;
        Avatar avatar = post.getEmployer().getAvatar();

        if(avatar != null) {
            postImageBytes = java.util.Base64.getDecoder().decode(avatar.getData());
        }
        if(post.getEmployer().getBackground() != null) {
            backgroundBytes = java.util.Base64.getDecoder().decode(post.getEmployer().getBackground());
        }

        String salary = post.getMinSalary() + "$" + " - " + post.getMaxSalary() + "$";

        Set<PostLevelDetail> postLevelDetails = postLevelDetailRepository.findAllByKeyPostLevelPostId(post.getPostId());
        List<PostLevelDTO> postLevelDTOS = new ArrayList<>();
        for(PostLevelDetail p : postLevelDetails) {
            PostLevelDTO postLevelDTO = PostLevelDTO.builder()
                    .id(p.getPostLevel().getLevelId())
                    .value(p.getPostLevel().getLevelTitle())
                    .build();
            postLevelDTOS.add(postLevelDTO);
        }

        Set<PostTypeDetail> postTypeDetails = postTypeDetailRepository.findAllByKeyPostTypePostId(post.getPostId());
        List<PostTypeDTO> postTypeDTOS = new ArrayList<>();
        for(PostTypeDetail p : postTypeDetails) {
            PostTypeDTO postTypeDTO = PostTypeDTO.builder()
                    .id(p.getPostType().getTypeId())
                    .value(p.getPostType().getTypeTitle())
                    .build();
            postTypeDTOS.add(postTypeDTO);
        }

        Set<PostContractDetail> postContractDetails = postContractDetailRepository.findAllByKeyPostContractPostId(post.getPostId());
        List<PostContractDTO> postContractDTOS = new ArrayList<>();
        for(PostContractDetail p : postContractDetails) {
            PostContractDTO postContractDTO = PostContractDTO.builder()
                    .id(p.getPostContract().getContractId())
                    .value(p.getPostContract().getContractTitle())
                    .build();
            postContractDTOS.add(postContractDTO);
        }

        PostRequirementDTO postRequirementDTO = getPostRequirement(post.getPostId());
        String location = post.getEmployer().getProvince() != null ?
                post.getEmployer().getProvince().getProvinceName() : "";
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");

        return PostDTO.builder()
                .postId(post.getPostId())
                .image(postImageBytes)
                .background(backgroundBytes)
                .status(post.getPostStatus().getStatusTitle())
                .title(post.getJobTitle())
                .desc(post.getJobDesc())
                .companyName(post.getEmployer().getCompanyName())
                .expiredDate(sf.format(post.getEndDate()))
                .location(location)
                .salary(salary)
                .levels(postLevelDTOS)
                .types(postTypeDTOS)
                .contracts(postContractDTOS)
                .tech(post.getTechnologies())
                .postRequirementDTO(postRequirementDTO)
                .build();
    }
}
