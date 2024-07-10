package com.jobseeking.jobseekingbe.service;

import com.jobseeking.jobseekingbe.dto.response.ProvinceDTO;
import com.jobseeking.jobseekingbe.entity.Province;
import com.jobseeking.jobseekingbe.repository.ProvinceRepository;
import com.jobseeking.jobseekingbe.service.imp.WebViewServiceImp;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class WebViewService implements WebViewServiceImp {

    ProvinceRepository provinceRepository;
    @Override
    public List<ProvinceDTO> getListProvince() {
        List<Province> provinces = provinceRepository.findAll();
        List<ProvinceDTO> provinceDTOS = new ArrayList<>();
        for(Province p : provinces) {
            ProvinceDTO provinceDTO = ProvinceDTO.builder()
                    .id(p.getProvinceId())
                    .value(p.getProvinceName())
                    .build();
            provinceDTOS.add(provinceDTO);
        }

        return provinceDTOS;
    }
}
