package com.jobseeking.jobseekingbe.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserUpdateRequest {
    String token;
    String fullname;
    String email;
    Date dob;
    String position;
    String currentSalary;
    String selfDesc;
    int age;
    String gender;
    int expectSalary;
    String facebook;
    String linkedin;
    String location;
}
