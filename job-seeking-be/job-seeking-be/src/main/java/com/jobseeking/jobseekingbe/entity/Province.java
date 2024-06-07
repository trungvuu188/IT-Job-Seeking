package com.jobseeking.jobseekingbe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Province {
    @Id
    private int pro_id;
    private String name;
    private String slug;
    private String type;
    private String nameWithType;
    private String code;

}
