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
    String title;
    String companyName;
    String location;
    Date expiredDate;
    String salary;
    Set<PostRequirement> requirements;
    Set<PostLevelDetail> levels;
    Set<PostTypeDetail> types;
    Set<PostContractDetail> contracts;
    String tech;
}
