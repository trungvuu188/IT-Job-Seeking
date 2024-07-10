package com.jobseeking.jobseekingbe.dto.response;

import com.jobseeking.jobseekingbe.entity.PostContractDetail;
import com.jobseeking.jobseekingbe.entity.PostLevelDetail;
import com.jobseeking.jobseekingbe.entity.PostRequirement;
import com.jobseeking.jobseekingbe.entity.PostTypeDetail;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostDTO {
    int postId;
    byte[] image;
    byte[] background;
    String status;
    String title;
    String desc;
    String companyName;
    String location;
    String expiredDate;
    String salary;
    List<PostLevelDTO> levels;
    List<PostTypeDTO> types;
    List<PostContractDTO> contracts;
    PostRequirementDTO postRequirementDTO;
    String tech;
}
