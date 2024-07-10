package com.jobseeking.jobseekingbe.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity(name = "provinces")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    int provinceId;

    @Column(name = "pro_name")
    String provinceName;

    @OneToMany(mappedBy = "province")
    List<Employer> employers;

    @OneToMany(mappedBy = "province")
    List<Candidate> candidates;
}
