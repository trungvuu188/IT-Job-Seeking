package com.jobseeking.jobseekingbe.service.imp;

import com.jobseeking.jobseekingbe.dto.request.AvatarUpdateRequest;
import com.jobseeking.jobseekingbe.entity.Avatar;

public interface FileStorageServiceImp {

    void save(AvatarUpdateRequest avatarUpdateRequest);

    void updateBackground(AvatarUpdateRequest avatarUpdateRequest);

    String getBackground(String userId);

    Avatar getAvatar(String userId);
}
