package com.jobseeking.jobseekingbe.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplyCVRequest {
    String userId;
    int cvId;
    int postId;
}
