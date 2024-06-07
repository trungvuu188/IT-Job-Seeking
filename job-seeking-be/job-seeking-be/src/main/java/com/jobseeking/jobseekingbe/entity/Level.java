package com.jobseeking.jobseekingbe.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "levels")
public class Level {

    @Id
    @Column(name = "level_id")
    private Integer levelId;

    @Column(name = "level_name", length = 100)
    private String levelName;

    // Getters and setters

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
