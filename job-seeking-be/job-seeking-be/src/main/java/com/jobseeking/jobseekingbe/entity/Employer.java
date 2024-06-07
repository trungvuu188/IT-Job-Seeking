package com.jobseeking.jobseekingbe.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employer")
public class Employer {

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

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
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

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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
