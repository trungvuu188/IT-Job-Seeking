package com.jobseeking.jobseekingbe.dto.request;

import com.jobseeking.jobseekingbe.entity.*;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PostCreationRequest {
    String title;
    String user_id;
    Long minSalary;
    Long maxSalary;
    Date endDate;
    Set<PostRequirement> postRequirements;
    Set<PostLevelDetail> postLevelDetails;
    Set<PostTypeDetail> postTypeDetails;
    Set<PostContractDetail> postContractDetails;
    String technologies;
    String welfare;
}
