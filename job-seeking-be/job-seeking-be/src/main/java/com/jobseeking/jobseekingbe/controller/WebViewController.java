package com.jobseeking.jobseekingbe.controller;

import com.jobseeking.jobseekingbe.dto.ApiResponse;
import com.jobseeking.jobseekingbe.dto.response.ProvinceDTO;
import com.jobseeking.jobseekingbe.service.imp.WebViewServiceImp;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/")
@RequiredArgsConstructor
public class WebViewController {

    WebViewServiceImp webViewServiceImp;

    @GetMapping()
    public ApiResponse<List<ProvinceDTO>> getAllProvinces() {
        return ApiResponse.<List<ProvinceDTO>>builder()
                .result(webViewServiceImp.getListProvince())
                .build();
    }
}
