package com.jobseeking.jobseekingbe.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "provinces")
public class Province {

    @Id
    @Column(name = "pro_id")
    private Integer proId;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "slug", length = 255)
    private String slug;

    @Column(name = "type", length = 255)
    private String type;

    @Column(name = "nameWithType", length = 255)
    private String nameWithType;

    @Column(name = "code", length = 255)
    private String code;

    // Getters and setters

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNameWithType() {
        return nameWithType;
    }

    public void setNameWithType(String nameWithType) {
        this.nameWithType = nameWithType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
