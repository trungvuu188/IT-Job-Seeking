package com.jobseeking.jobseekingbe.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EmployerDTO {
    String id;
    String email;
    byte[] image;
    String companyName;
    String phone;
    String website;
    String companyDesc;
    String facebook;
    String linkedIn;
    int locationId;

}
