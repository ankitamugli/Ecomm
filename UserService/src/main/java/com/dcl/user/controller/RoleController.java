package com.dcl.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dcl.user.ApiResponse;
import com.dcl.user.dto.RoleDto;
import com.dcl.user.request.AddRoleRequest;
import com.dcl.user.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService rservice;

    @PostMapping("/add")
    public ResponseEntity<?> addRole(@RequestBody AddRoleRequest request) {

        RoleDto dto = rservice.addRole(request.getRoleName());

        return ResponseEntity.ok(
            new ApiResponse<>(
                "role added successfully!",
                dto,
                HttpStatus.OK
            )
        );
    }
}