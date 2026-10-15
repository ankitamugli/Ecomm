package com.dcl.user.service;

import com.dcl.user.dto.RoleDto;
import com.dcl.user.enums.RoleType;

public interface RoleService {

    RoleDto addRole(RoleType roleName);

    RoleDto getRoleByRoleName(RoleType roleName);

    RoleDto getRoleById(Integer roleId);
}