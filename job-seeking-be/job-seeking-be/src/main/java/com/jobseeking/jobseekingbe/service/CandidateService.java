package com.jobseeking.jobseekingbe.service;

import com.jobseeking.jobseekingbe.dto.request.AvatarUpdateRequest;
import com.jobseeking.jobseekingbe.dto.request.CandidateUpdateRequest;
import com.jobseeking.jobseekingbe.dto.response.CandidateDTO;
import com.jobseeking.jobseekingbe.entity.Candidate;
import com.jobseeking.jobseekingbe.entity.Province;
import com.jobseeking.jobseekingbe.repository.CandidateRepository;
import com.jobseeking.jobseekingbe.repository.ProvinceRepository;
import com.jobseeking.jobseekingbe.repository.UserRepository;
import com.jobseeking.jobseekingbe.service.imp.CandidateServiceImp;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CandidateService implements CandidateServiceImp {

    CandidateRepository candidateRepository;
    ProvinceRepository provinceRepository;

    @Override
    public Candidate getCandidateById(String id) {
        Candidate candidate = candidateRepository.findCandidateById(id);
        if(candidate == null) {
            throw new RuntimeException("Candidate is not found");
        }
        return candidate;
    }

    @Override
    public CandidateDTO getCandidateInfo(String id) {
        Candidate candidate = getCandidateById(id);
        String dob = "";
        if(candidate.getDob() != null) {
            dob = formatDate(candidate.getDob());
        }
        return CandidateDTO.builder()
                .email(candidate.getEmail() != null ? candidate.getEmail() : "")
                .fullName(candidate.getFullName() != null ? candidate.getFullName() : "")
                .dob(dob)
                .gender(candidate.getGender() != null ? candidate.getGender() : "")
                .phone(candidate.getPhone() != null ? candidate.getPhone() : "")
                .workPosition(candidate.getPosition() != null ? candidate.getPosition() : "")
                .currentSalary(candidate.getCurrentSalary() != null ? candidate.getCurrentSalary() : "")
                .selfDesc(candidate.getSelfDesc() != null ? candidate.getSelfDesc() : "")
                .age(candidate.getAge() != null ? candidate.getAge() : "")
                .salaryExpect(candidate.getSalaryExpect() != null ? candidate.getSalaryExpect() : "")
                .facebook(candidate.getFacebook() != null ? candidate.getFacebook() : "")
                .linkedIn(candidate.getLinkedin() != null ? candidate.getLinkedin() : "")
                .location(candidate.getProvince() != null ? candidate.getProvince().getProvinceName() : "")
                .build();
    }

    @Override
    public boolean updateCandidateInfo(String id, CandidateUpdateRequest candidateUpdateRequest) throws ParseException {
        Candidate candidate = getCandidateById(id);
        if(!candidateUpdateRequest.getFullName().isEmpty()) {
            candidate.setFullName(candidateUpdateRequest.getFullName());
        }
        if(!candidateUpdateRequest.getDob().isEmpty()) {
            candidate.setDob(parseDate(candidateUpdateRequest.getDob()));
        }
        if(!candidateUpdateRequest.getGender().isEmpty()) {
            candidate.setGender(candidateUpdateRequest.getGender());
        }
        if(!candidateUpdateRequest.getPhone().isEmpty()) {
            candidate.setPhone(candidateUpdateRequest.getPhone());
        }
        if(!candidateUpdateRequest.getWorkPosition().isEmpty()) {
            candidate.setPosition(candidateUpdateRequest.getWorkPosition());
        }
        if(!candidateUpdateRequest.getCurrentSalary().isEmpty()) {
            candidate.setCurrentSalary(candidateUpdateRequest.getCurrentSalary());
        }
        if(!candidateUpdateRequest.getSelfDesc().isEmpty()) {
            candidate.setSelfDesc(candidateUpdateRequest.getSelfDesc());
        }
        if(!candidateUpdateRequest.getAge().isEmpty()) {
            candidate.setAge(candidateUpdateRequest.getAge());
        }
        if(!candidateUpdateRequest.getSalaryExpect().isEmpty()) {
            candidate.setSalaryExpect(candidateUpdateRequest.getSalaryExpect());
        }
        if(!candidateUpdateRequest.getFacebook().isEmpty()) {
            candidate.setFacebook(candidateUpdateRequest.getFacebook());
        }
        if(!candidateUpdateRequest.getLinkedIn().isEmpty()) {
            candidate.setLinkedin(candidateUpdateRequest.getLinkedIn());
        }
        if(candidateUpdateRequest.getLocation() != 0) {
            Province province = provinceRepository.findById(candidateUpdateRequest.getLocation())
                    .orElseThrow(() -> new RuntimeException("Province not found"));
            candidate.setProvince(province);
        }
        candidateRepository.save(candidate);
        return true;
    }


    public Date parseDate(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy");
        return simpleDateFormat.parse(date);
    }

    public String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy");
        return simpleDateFormat.format(date);
    }
}
