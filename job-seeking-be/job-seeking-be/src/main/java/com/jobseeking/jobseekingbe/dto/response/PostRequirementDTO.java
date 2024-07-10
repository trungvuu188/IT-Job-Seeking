package com.jobseeking.jobseekingbe.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostRequirementDTO {
    String [] role;
    String [] skill;
    String [] benefit;
    String [] progress;
    String [] minimum;
}
