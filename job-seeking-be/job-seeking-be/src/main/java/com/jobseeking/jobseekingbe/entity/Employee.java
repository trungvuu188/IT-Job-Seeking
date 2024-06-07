package com.jobseeking.jobseekingbe.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Employee {

    @Id
    private String emp_id;

    private String emp_name;
    private String email;
    private String phone;
    private Date dob;
    private boolean gender;
    private String cv;
    private String cover_letter;
    private String avatar;
    private boolean notification;
    private long desired_salary;
    private String school;
    private String github;

    @ManyToOne
    @JoinColumn(name = "pro_id", referencedColumnName = "pro_id")
    private Province province;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public Employee() {
        // Default constructor
    }

    public Employee(String emp_id, String emp_name, String email, String phone, Date dob, boolean gender, String cv,
                    String cover_letter, String avatar, boolean notification, long desired_salary, String school, String github,
                    Province province, User user) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.cv = cv;
        this.cover_letter = cover_letter;
        this.avatar = avatar;
        this.notification = notification;
        this.desired_salary = desired_salary;
        this.school = school;
        this.github = github;
        this.province = province;
        this.user = user;
    }

    // Getter and Setter methods

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getCover_letter() {
        return cover_letter;
    }

    public void setCover_letter(String cover_letter) {
        this.cover_letter = cover_letter;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    public long getDesired_salary() {
        return desired_salary;
    }

    public void setDesired_salary(long desired_salary) {
        this.desired_salary = desired_salary;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
