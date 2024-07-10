package com.jobseeking.jobseekingbe.dto.request;

import com.jobseeking.jobseekingbe.entity.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PostCreationRequest {
    String position;
    String jobDetail;
    String email;
    String companyName;
    Long minSalary;
    Long maxSalary;
    Date endDate;
    String tech;
    String [] level;
    String [] jobType;
    String [] jobContract;
    String [] benefit;
    String [] progress;
    String [] role;
    String [] skill;
    String [] minimumYear;
}
