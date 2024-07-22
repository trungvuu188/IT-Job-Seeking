package com.jobseeking.jobseekingbe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity(name = "admins")
@Data
@PrimaryKeyJoinColumn(name="user_id")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Admin extends User {

    @Column(name = "fullname")
    String fullName;

    @Builder
    public Admin(String email, String password, String phone, Role role, String fullName) {
        super(email, password, phone, role, false);
        this.fullName = fullName;
    }
}
