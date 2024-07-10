package com.jobseeking.jobseekingbe.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity(name = "avatar")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Avatar {

    @Id
    @Column(name = "user_id")
    String userId;

    @Column(name = "name")
    String name;

    @Column(name = "type")
    String type;

    @Lob
    @Column(name = "data")
    String data;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    User user;
}
