package com.jobseeking.jobseekingbe.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;


@Entity(name = "candidates")
@Data
@PrimaryKeyJoinColumn(name="user_id")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Candidate extends User {

    @Column(name = "avatar")
    String avatar;

    @Column(name = "dob")
    Date dob;

    @Column(name = "position")
    String position;

    @Column(name = "current_salary")
    String currentSalary;

    @Column(name = "self_desc")
    String selfDesc;

    @Column(name = "gender")
    String gender;

    @Column(name = "age")
    int age;

    @Column(name = "salary_expect")
    String salaryExpect;

    @Column(name = "facebook")
    String facebook;

    @Column(name = "linkedin")
    String linkedin;

    @Column(name = "location")
    String location;

    @Builder
    public Candidate(String email, String password, String phone, Role role, String avatar, Date dob, String position, String currentSalary, String selfDesc, String gender, int age, String salaryExpect, String facebook, String linkedin, String location) {
        super(email, password, phone, role);
        this.avatar = avatar;
        this.dob = dob;
        this.position = position;
        this.currentSalary = currentSalary;
        this.selfDesc = selfDesc;
        this.gender = gender;
        this.age = age;
        this.salaryExpect = salaryExpect;
        this.facebook = facebook;
        this.linkedin = linkedin;
        this.location = location;
    }
}
