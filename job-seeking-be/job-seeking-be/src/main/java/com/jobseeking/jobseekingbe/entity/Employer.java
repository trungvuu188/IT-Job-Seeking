package com.jobseeking.jobseekingbe.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;


@Entity(name = "employers")
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(name="user_id")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employer extends User {

    @Column(name = "avatar")
    String avatar;

    @Column(name = "emp_name")
    String companyName;

    @Column(name = "website")
    String website;

    @Column(name = "about_us")
    String about;

    @Column(name = "address")
    String address;

    @Column(name = "facebook")
    String facebook;

    @Column(name = "linkedin")
    String linkedin;

    @Column(name = "location")
    String location;

    @Column(name = "pro_id")
    String pro_id;

    @Builder
    public Employer(String email, String password, String phone, Role role, String avatar, String companyName, String website, String about, String address, String facebook, String linkedin, String location, String pro_id, Set<Post> posts) {
        super(email, password, phone, role);
        this.avatar = avatar;
        this.companyName = companyName;
        this.website = website;
        this.about = about;
        this.address = address;
        this.facebook = facebook;
        this.linkedin = linkedin;
        this.location = location;
        this.pro_id = pro_id;
        this.posts = posts;
    }

    @OneToMany(mappedBy = "employer")
    Set<Post> posts;
}
