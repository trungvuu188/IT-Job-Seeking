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

    public int getRoleId() {
        int roleId = 0;
        switch (accountType) {
            case "admin":
                roleId = 1;
                break;
            case "candidate":
                roleId = 2;
                break;
            case "employer":
                roleId = 3;
                break;
        }
        return roleId;
    }
}
