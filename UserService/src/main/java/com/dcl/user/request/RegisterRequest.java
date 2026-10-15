package com.dcl.user.request;

import com.dcl.user.enums.RoleType;
import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private RoleType roleName;
}