package com.dcl.user.service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dcl.user.dto.RoleDto;
import com.dcl.user.entity.Role;
import com.dcl.user.enums.RoleType;
import com.dcl.user.exception.AppException;
import com.dcl.user.repo.RoleRepo;
import com.dcl.user.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo rrepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public RoleDto addRole(RoleType roleName) {

        if (roleName == null) {
            throw new AppException(
                    "role name cannot be null",
                    HttpStatus.BAD_REQUEST);
        }

        Role r = rrepo.findByRoleName(roleName).orElse(null);

        if (r != null) {
            throw new AppException(
                    "role already exists!",
                    HttpStatus.CONFLICT);
        }

        r = new Role();
        r.setRoleName(roleName);

        r = rrepo.save(r);

        return mapper.map(r, RoleDto.class);
    }

    @Override
    public RoleDto getRoleByRoleName(RoleType roleName) {

        if (roleName == null) {
            throw new AppException(
                    "role name cannot be null",
                    HttpStatus.BAD_REQUEST);
        }

        Role r = rrepo.findByRoleName(roleName)
                .orElseThrow(() ->
                        new AppException(
                                "no role found!",
                                HttpStatus.NOT_FOUND));

        return mapper.map(r, RoleDto.class);
    }

    @Override
    public RoleDto getRoleById(Integer roleId) {

        Role r = rrepo.findById(roleId)
                .orElseThrow(() ->
                        new AppException(
                                "no role found!",
                                HttpStatus.NOT_FOUND));

        return mapper.map(r, RoleDto.class);
    }
}