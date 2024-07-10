package com.jobseeking.jobseekingbe.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CandidateDTO {
    String email;
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
    String location;
}
