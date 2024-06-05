package com.jobseeking.jobseekingbe.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCreationRequest {
    String accountType;
    String email;
    String password;
    String phone;
    String companyName;
//    ADMIN 1
//    EMPLOYEE 2
//    EMPLOYER 3
    public int getRoleId() {
        return accountType.equals("candidate") ? 2 : 3;
    }
}
