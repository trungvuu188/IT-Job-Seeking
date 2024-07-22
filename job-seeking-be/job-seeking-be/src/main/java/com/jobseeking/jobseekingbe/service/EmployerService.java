package com.jobseeking.jobseekingbe.service;

import com.jobseeking.jobseekingbe.dto.request.CandidateSaveRequest;
import com.jobseeking.jobseekingbe.dto.request.CompanySearchRequest;
import com.jobseeking.jobseekingbe.dto.request.EmployerUpdateRequest;
import com.jobseeking.jobseekingbe.dto.request.PostCreationRequest;
import com.jobseeking.jobseekingbe.dto.response.*;
import com.jobseeking.jobseekingbe.entity.*;
import com.jobseeking.jobseekingbe.entity.keys.KeyEmployerCandidate;
import com.jobseeking.jobseekingbe.repository.*;
import com.jobseeking.jobseekingbe.service.imp.EmployerServiceImp;
import com.jobseeking.jobseekingbe.service.imp.PostServiceImp;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EmployerService implements EmployerServiceImp {

    EmployerRepository employerRepository;
    ProvinceRepository provinceRepository;
    PostRepository postRepository;
    CandidateApplyRepository candidateApplyRepository;
    PostApplyRepository postApplyRepository;
    PostApplyStatusRepository postApplyStatusRepository;
    CandidateRepository candidateRepository;
    CVRepository cvRepository;
    CandidateSaveRepository candidateSaveRepository;

    @Override
    public List<CompanyDTO> getAllCompany() {
        List<Employer> employers = employerRepository.findAll();
        List<CompanyDTO> companyDTOS = new ArrayList<>();
        for ( Employer employer : employers ) {
            CompanyDTO companyDTO = CompanyDTO.builder()
                    .isBan(employer.getIsBan())
                    .companyId(employer.getId())
                    .locationName(employer.getProvince() != null ? employer.getProvince().getProvinceName() : "")
                    .companyName(employer.getCompanyName())
                    .image(employer.getAvatar() != null ? java.util.Base64.getDecoder().decode(employer.getAvatar().getData()) : null)
                    .postCount(postRepository.getAllByEmployerId(employer.getId()).size())
                    .build();
            companyDTOS.add(companyDTO);
        }
        return companyDTOS;
    }

    @Override
    public Employer getEmployerById(String id) {
        Employer employer = employerRepository.findEmployerById(id);
        if(employer == null) {
            throw new RuntimeException("Employer is not found");
        }
        return employer;
    }

    @Override
    public String saveEmployer(Employer employer) {
        employerRepository.save(employer);
        return employer.getId();
    }

    @Override
    public EmployerDTO getEmployerInfo(String id) {
        Employer employer = getEmployerById(id);

        return EmployerDTO.builder()
                .email(employer.getEmail() != null ? employer.getEmail() : "")
                .companyName(employer.getCompanyName() != null ? employer.getCompanyName() : "")
                .phone(employer.getPhone() != null ? employer.getPhone() : "")
                .website(employer.getWebsite() != null ? employer.getWebsite() : "")
                .companyDesc(employer.getAbout() != null ? employer.getAbout() : "")
                .facebook(employer.getFacebook() != null ? employer.getFacebook() : "")
                .linkedIn(employer.getLinkedin() != null ? employer.getLinkedin() : "")
                .locationId(employer.getProvince() != null ? employer.getProvince().getProvinceId() : 0)
                .build();
    }

    @Override
    public boolean updateEmployerInfo(String id, EmployerUpdateRequest employerUpdateRequest) {

        Employer employer = getEmployerById(id);

        if(!employerUpdateRequest.getCompanyName().isEmpty()) {
            employer.setCompanyName(employerUpdateRequest.getCompanyName());
        }
        if(!employerUpdateRequest.getPhone().isEmpty()) {
            employer.setPhone(employerUpdateRequest.getPhone());
        }
        if(!employerUpdateRequest.getWebsite().isEmpty()) {
            employer.setWebsite(employerUpdateRequest.getWebsite());
        }
        if(!employerUpdateRequest.getCompanyDesc().isEmpty()) {
            employer.setAbout(employerUpdateRequest.getCompanyDesc());
        }
        if(!employerUpdateRequest.getFacebook().isEmpty()) {
            employer.setFacebook(employerUpdateRequest.getFacebook());
        }
        if(!employerUpdateRequest.getLinkedIn().isEmpty()) {
            employer.setLinkedin(employerUpdateRequest.getLinkedIn());
        }
        if(employerUpdateRequest.getLocation() != 0) {
            Province province = provinceRepository.findById(employerUpdateRequest.getLocation())
                    .orElseThrow(() -> new RuntimeException("Province not found"));
            employer.setProvince(province);
        }
        employerRepository.save(employer);
        return true;
    }

    @Override
    public boolean acceptCandidate(String candidateId, int postId) {

        PostApplyStatus postApplyStatus = postApplyStatusRepository.findByStatusTitle("ACCEPT");
        PostApply postApply = postApplyRepository.findByKeyPostCandidateCandidateIdAndAndKeyPostCandidatePostId(candidateId, postId);
        postApply.setPostApplyStatus(postApplyStatus);
        postApplyRepository.save(postApply);

        CandidateApply candidateApply = candidateApplyRepository.findByKeyPostCandidateCandidateIdAndKeyPostCandidatePostId(candidateId, postId);
        candidateApply.setPostApplyStatus(postApplyStatus);
        candidateApplyRepository.save(candidateApply);

        return true;
    }

    @Override
    public boolean rejectCandidate(String candidateId, int postId) {

        PostApplyStatus postApplyStatus = postApplyStatusRepository.findByStatusTitle("REJECTED");
        PostApply postApply = postApplyRepository.findByKeyPostCandidateCandidateIdAndAndKeyPostCandidatePostId(candidateId, postId);
        postApply.setPostApplyStatus(postApplyStatus);
        postApplyRepository.save(postApply);

        CandidateApply candidateApply = candidateApplyRepository.findByKeyPostCandidateCandidateIdAndKeyPostCandidatePostId(candidateId, postId);
        candidateApply.setPostApplyStatus(postApplyStatus);
        candidateApplyRepository.save(candidateApply);

        return true;
    }

    @Override
    public boolean saveCandidate(CandidateSaveRequest candidateSaveRequest) {

        Candidate candidate = candidateRepository.findCandidateById(candidateSaveRequest.getCandidateId());
        Employer employer = employerRepository.findEmployerById(candidateSaveRequest.getEmployerId());

        KeyEmployerCandidate keyEmployerCandidate = KeyEmployerCandidate.builder()
                .employerId(candidateSaveRequest.getEmployerId())
                .candidateId(candidateSaveRequest.getCandidateId())
                .build();

        CandidateSave candidateSave = CandidateSave.builder()
                .keyEmployerCandidate(keyEmployerCandidate)
                .candidate(candidate)
                .employer(employer)
                .cvData(candidateSaveRequest.getCvData())
                .build();
        candidateSaveRepository.save(candidateSave);

        return true;
    }

    @Override
    public List<CandidateApplyDTO> getAllCandidateSaved(String userId) {
        List<CandidateApplyDTO> candidateApplyDTOS = new ArrayList<>();
        List<CandidateSave> candidateSaves = candidateSaveRepository.findAllByKeyEmployerCandidateEmployerId(userId);
        for ( CandidateSave candidateSave : candidateSaves ) {
            CandidateApplyDTO candidateApplyDTO = CandidateApplyDTO.builder()
                    .candidateId(candidateSave.getCandidate().getId())
                    .candidateName(candidateSave.getCandidate().getFullName())
                    .cvData(java.util.Base64.getDecoder().decode(candidateSave.getCvData()))
                    .avatar(candidateSave.getCandidate().getAvatar() != null ? java.util.Base64.getDecoder().decode(candidateSave.getCandidate().getAvatar().getData()) : null)
                    .build();
            candidateApplyDTOS.add(candidateApplyDTO);
        }
        return candidateApplyDTOS;
    }

    @Transactional
    @Override
    public boolean deleteCandidateSaved(String empId, String candidateId) {
        candidateSaveRepository.deleteByKeyEmployerCandidateEmployerIdAndKeyEmployerCandidateCandidateId(empId, candidateId);
        return true;
    }

    @Transactional
    @Override
    public boolean deleteCandidateApply(String candidateId, int postId) {
        postApplyRepository.deleteByKeyPostCandidateCandidateIdAndKeyPostCandidatePostId(candidateId, postId);
        return true;
    }

    @Override
    public List<CompanyDTO> filterCompany(CompanySearchRequest companySearchRequest) {

        String input = companySearchRequest.getSearchInput();
        int locationId = companySearchRequest.getSelectedOption();
        if(input.isEmpty()) {
            return filterCompanyByLocation(locationId);
        }
        if(!input.isEmpty() && locationId == 0) {
            return filterCompanyByName(input);
        }
        return filterCompanyByNameAndLocation(input, locationId);
    }

    @Override
    public List<CandidateApplyDTO> getAllCandidateApply(String userId) {

        List<Post> posts = postRepository.getAllByEmployerId(userId);
        List<CandidateApplyDTO> candidateApplyDTOS = new ArrayList<>();
        for ( Post post : posts ) {
            List<CandidateApply> candidateApplies = candidateApplyRepository.findAllByKeyPostCandidatePostId(post.getPostId());
            for ( CandidateApply candidateApply : candidateApplies ) {
                if(candidateApply.getPost().getPostId() == post.getPostId()) {
                    CandidateApplyDTO candidateApplyDTO = CandidateApplyDTO.builder()
                            .candidateId(candidateApply.getCandidate().getId())
                            .postId(candidateApply.getPost().getPostId())
                            .status(candidateApply.getPostApplyStatus().getStatusTitle())
                            .avatar(candidateApply.getCandidate().getAvatar() != null ? java.util.Base64.getDecoder().decode(candidateApply.getCandidate().getAvatar().getData()) : null)
                            .candidateName(candidateApply.getCandidate().getFullName())
                            .postName(candidateApply.getPost().getJobTitle())
                            .cvData(java.util.Base64.getDecoder().decode(candidateApply.getCvData()))
                            .build();
                    candidateApplyDTOS.add(candidateApplyDTO);
                }
            }
        }

        return candidateApplyDTOS;
    }

    public List<CompanyDTO> filterCompanyByName(String companyName) {
        List<Employer> employers = employerRepository.findAll();
        List<CompanyDTO> companyDTOS = new ArrayList<>();
        for ( Employer employer : employers ) {
            if(employer.getCompanyName().toUpperCase().contains(companyName.toUpperCase())) {
                CompanyDTO companyDTO = CompanyDTO.builder()
                        .companyId(employer.getId())
                        .locationName(employer.getProvince() != null ? employer.getProvince().getProvinceName() : "")
                        .companyName(employer.getCompanyName())
                        .image(java.util.Base64.getDecoder().decode(employer.getAvatar().getData()))
                        .postCount(postRepository.getAllByEmployerId(employer.getId()).size())
                        .build();
                companyDTOS.add(companyDTO);
            }
        }
        return companyDTOS;
    }

    public List<CompanyDTO> filterCompanyByLocation(int locationId) {
        List<Employer> employers = employerRepository.findAll();
        List<CompanyDTO> companyDTOS = new ArrayList<>();
        for ( Employer employer : employers ) {
            if(employer.getProvince() != null && employer.getProvince().getProvinceId() == locationId) {
                CompanyDTO companyDTO = CompanyDTO.builder()
                        .companyId(employer.getId())
                        .locationName(employer.getProvince() != null ? employer.getProvince().getProvinceName() : "")
                        .companyName(employer.getCompanyName())
                        .image(java.util.Base64.getDecoder().decode(employer.getAvatar().getData()))
                        .postCount(postRepository.getAllByEmployerId(employer.getId()).size())
                        .build();
                companyDTOS.add(companyDTO);
            }
        }
        return companyDTOS;
    }

    public List<CompanyDTO> filterCompanyByNameAndLocation(String input, int locationId) {
        List<Employer> employers = employerRepository.findAll();
        List<CompanyDTO> companyDTOS = new ArrayList<>();
        for ( Employer employer : employers ) {
            if(employer.getCompanyName().toUpperCase().contains(input.toUpperCase())
                && employer.getProvince() != null && employer.getProvince().getProvinceId() == locationId) {
                CompanyDTO companyDTO = CompanyDTO.builder()
                        .companyId(employer.getId())
                        .locationName(employer.getProvince() != null ? employer.getProvince().getProvinceName() : "")
                        .companyName(employer.getCompanyName())
                        .image(java.util.Base64.getDecoder().decode(employer.getAvatar().getData()))
                        .postCount(postRepository.getAllByEmployerId(employer.getId()).size())
                        .build();
                companyDTOS.add(companyDTO);
            }
        }
        return companyDTOS;
    }

}
