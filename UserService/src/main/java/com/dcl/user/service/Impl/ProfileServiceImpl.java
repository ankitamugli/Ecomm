package com.dcl.user.service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dcl.user.dto.ProfileDto;
import com.dcl.user.entity.Profile;
import com.dcl.user.exception.AppException;
import com.dcl.user.repo.ProfileRepo;
import com.dcl.user.request.profileUpdateRequest;
import com.dcl.user.response.CloudinaryResponse;
import com.dcl.user.service.CloudinaryService;
import com.dcl.user.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepo prepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CloudinaryService cservice;

    @Override
    public Profile addProfile(Profile profile) {
        return prepo.save(profile);
    }

    @Override
    public ProfileDto updateProfile(
            Integer profileId,
            profileUpdateRequest request,
            MultipartFile image) {

        Profile p = prepo.findById(profileId)
                .orElseThrow(() ->
                        new AppException(
                                "No profile found!",
                                HttpStatus.NOT_FOUND));

        mapper.map(request, p);

        if (image != null && !image.isEmpty()) {

            if (p.getImageUrl() != null && p.getPublicUrl() != null) {
                cservice.deleteImage(p.getPublicUrl());
            }

            CloudinaryResponse response = cservice.uploadImage(image);

            p.setImageUrl(response.getImageUrl());
            p.setPublicUrl(response.getPublicId());
        }

        p = prepo.save(p);

        return mapper.map(p, ProfileDto.class);
    }

    @Override
    public void deleteProfile(Integer profileId) {

        prepo.findById(profileId)
                .orElseThrow(() ->
                        new AppException(
                                "No profile found!",
                                HttpStatus.NOT_FOUND));

        prepo.deleteById(profileId);
    }

    @Override
    public ProfileDto getByProfileId(Integer profileId) {

        Profile p = prepo.findById(profileId)
                .orElseThrow(() ->
                        new AppException(
                                "No profile found!",
                                HttpStatus.NOT_FOUND));

        return mapper.map(p, ProfileDto.class);
    }

    @Override
    public ProfileDto getProfileByUserId(Integer userId) {

        Profile p = prepo.findByUserUserId(userId)
                .orElseThrow(() ->
                        new AppException(
                                "No profile found!",
                                HttpStatus.NOT_FOUND));

        return mapper.map(p, ProfileDto.class);
    }
}