package com.jobseeking.jobseekingbe.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostApplyDTO {
    int postId;
    byte[] image;
    String title;
    String companyName;
    String applicationDate;
    String status;
}
