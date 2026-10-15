package com.dcl.user.service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dcl.user.dto.ProfileDto;
import com.dcl.user.dto.RoleDto;
import com.dcl.user.dto.UserDto;
import com.dcl.user.entity.Profile;
import com.dcl.user.entity.Role;
import com.dcl.user.entity.User;
import com.dcl.user.exception.AppException;
import com.dcl.user.repo.UserRepo;
import com.dcl.user.request.LoginRequest;
import com.dcl.user.request.RegisterRequest;
import com.dcl.user.service.MailSender;
import com.dcl.user.service.ProfileService;
import com.dcl.user.service.RoleService;
import com.dcl.user.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo urepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RoleService rservice;

    @Autowired
    private ProfileService pservice;

    @Autowired
    private MailSender mservice;

    @Transactional
    @Override
    public UserDto register(RegisterRequest request) {

        if (request.getRoleName() == null) {
            throw new AppException(
                    "role name is required",
                    HttpStatus.BAD_REQUEST);
        }

        RoleDto existingRole = rservice.getRoleByRoleName(
                request.getRoleName());

        User existingUser = urepo.findByEmail(
                request.getEmail()).orElse(null);

        if (existingUser != null) {
            throw new AppException(
                    "user already exists!",
                    HttpStatus.CONFLICT);
        }

        User newUser = mapper.map(request, User.class);

        Role role = mapper.map(existingRole, Role.class);

        newUser.setRole(role);

        newUser = urepo.save(newUser);

        Profile profile = mapper.map(request, Profile.class);

        profile.setUser(newUser);

        profile = pservice.addProfile(profile);

        try {
            mservice.sendMail(
                    request.getEmail(),
                    "Successfully Account Created",
                    "Hello " + request.getName()
                            + ", your account has been created successfully!");
        } catch (Exception e) {
            System.out.println(
                    "Mail sending failed: " + e.getMessage());
        }

        UserDto dto = mapper.map(newUser, UserDto.class);

        ProfileDto profileDto = mapper.map(
                profile, ProfileDto.class);

        dto.setProfileDto(profileDto);
        dto.setRoleDto(existingRole);

        return dto;
    }

    @Override
    public UserDto login(LoginRequest request) {

        User user = urepo.findByEmail(
                request.getEmail())
                .orElseThrow(() ->
                        new AppException(
                                "User Not Found!",
                                HttpStatus.NOT_FOUND));

        if (!user.getPassword()
                .equals(request.getPassword())) {

            throw new AppException(
                    "incorrect password!",
                    HttpStatus.BAD_REQUEST);
        }

        try {
            mservice.sendMail(
                    request.getEmail(),
                    "Happy Shopping!",
                    "You logged in successfully!");
        } catch (Exception e) {
            System.out.println(
                    "Mail sending failed: " + e.getMessage());
        }

        UserDto dto = mapper.map(
                user, UserDto.class);

        ProfileDto profileDto = pservice.getProfileByUserId(
                user.getUserId());

        RoleDto roleDto = rservice.getRoleById(
                user.getRole().getRoleId());

        dto.setProfileDto(profileDto);
        dto.setRoleDto(roleDto);

        return dto;
    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user = urepo.findById(userId)
                .orElseThrow(() ->
                        new AppException(
                                "no user found!",
                                HttpStatus.NOT_FOUND));

        ProfileDto profileDto = pservice.getProfileByUserId(
                userId);

        RoleDto roleDto = rservice.getRoleById(
                user.getRole().getRoleId());

        UserDto dto = mapper.map(
                user, UserDto.class);

        dto.setProfileDto(profileDto);
        dto.setRoleDto(roleDto);

        return dto;
    }

    @Transactional
    @Override
    public void deleteByUserId(Integer userId) {

        User user = urepo.findById(userId)
                .orElseThrow(() ->
                        new AppException(
                                "no user found!",
                                HttpStatus.NOT_FOUND));

        String email = user.getEmail();

        Profile profile = user.getProfile();

        if (profile != null) {
            pservice.deleteProfile(
                    profile.getProfileId());
        }

        urepo.delete(user);

        try {
            mservice.sendMail(
                    email,
                    "Account Deletion",
                    "Your account was deleted successfully!");
        } catch (Exception e) {
            System.out.println(
                    "Mail sending failed: " + e.getMessage());
        }
    }
}