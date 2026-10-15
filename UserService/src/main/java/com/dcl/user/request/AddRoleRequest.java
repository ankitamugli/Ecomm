package com.dcl.user.request;

import com.dcl.user.enums.RoleType;
import lombok.Data;

@Data
public class AddRoleRequest {

    private RoleType roleName;
}