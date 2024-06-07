package com.jobseeking.jobseekingbe.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @Column(name = "com_id", length = 50)
    private String comId;

    @Column(name = "com_name", nullable = false, length = 100)
    private String comName;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "about_us", length = 500)
    private String aboutUs;

    @Column(name = "logo", length = 200)
    private String logo;

    @ManyToOne
    @JoinColumn(name = "pro_id")
    private Province province;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and setters
}
