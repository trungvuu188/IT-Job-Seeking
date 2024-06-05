package com.jobseeking.jobseekingbe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity(name = "candidates")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Candidate extends User {

    @OneToOne(mappedBy = "candidates")
    User user;

    @Column(name = "fullname")
    String fullname;

    @Column(name = "dob")
    Date dob;

    @Column(name = "dob")
    String position;
    String currenSalary;
    String selfDesc;
    String gender;
    int age;
    String salaryExpect;
    String facebook;
    String linkedin;
    String location;


//    avatar text,
//    fullname varchar(100),
//    dob timestamp,
//    position varchar(255),
//    currentSalary varchar(255),
//    self_desc text,
//    gender enum('male', 'female'),
//    age int,
//    salary_expect varchar(255),
//    facebook varchar(255),
//    linkedin varchar(255),
//    location varchar(255),
}
