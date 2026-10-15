package com.dcl.user.service.Impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dcl.user.exception.AppException;
import com.dcl.user.response.CloudinaryResponse;
import com.dcl.user.service.CloudinaryService;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public CloudinaryResponse uploadImage(MultipartFile image) {

        CloudinaryResponse response = null;

        try {
            if (image != null && !image.isEmpty()) {

                Map<?, ?> result = cloudinary.uploader()
                        .upload(image.getBytes(), ObjectUtils.emptyMap());

                String publicId = result.get("public_id").toString();
                String imageUrl = result.get("secure_url").toString();

                response = new CloudinaryResponse(imageUrl, publicId);
            }

        } catch (IOException e) {
            throw new AppException(
                    "Failed to upload image",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return response;
    }

    @Override
    public void deleteImage(String publicId) {

        try {
            Map<?, ?> result = cloudinary.uploader()
                    .destroy(publicId, ObjectUtils.emptyMap());

            String status = result.get("result").toString();

            if (!status.equalsIgnoreCase("ok")) {
                throw new AppException(
                        "An error occurred while deleting image",
                        HttpStatus.BAD_REQUEST
                );
            }

        } catch (IOException e) {
            throw new AppException(
                    "Failed to delete image",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}