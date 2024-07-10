package com.jobseeking.jobseekingbe.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CandidateUpdateRequest {
    String fullName;
    String dob;
    String phone;
    String workPosition;
    String currentSalary;
    String selfDesc;
    String age;
    String gender;
    String salaryExpect;
    String facebook;
    String linkedIn;
    int location;
}
