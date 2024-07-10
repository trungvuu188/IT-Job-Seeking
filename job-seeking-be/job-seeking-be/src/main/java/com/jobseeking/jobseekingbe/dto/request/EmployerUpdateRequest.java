package com.jobseeking.jobseekingbe.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EmployerUpdateRequest {
    String companyName;
    String phone;
    String website;
    String companyDesc;
    String facebook;
    String linkedIn;
    int location;
}
