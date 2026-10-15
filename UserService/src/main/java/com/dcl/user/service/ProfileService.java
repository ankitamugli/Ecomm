package com.dcl.user.service;

import org.springframework.web.multipart.MultipartFile;

import com.dcl.user.dto.ProfileDto;
import com.dcl.user.entity.Profile;
import com.dcl.user.request.profileUpdateRequest;

public interface ProfileService {

    Profile addProfile(Profile profile);

    ProfileDto updateProfile(
            Integer profileId,
            profileUpdateRequest request,
            MultipartFile image);

    void deleteProfile(Integer profileId);

    ProfileDto getByProfileId(Integer profileId);

    ProfileDto getProfileByUserId(Integer userId);
}