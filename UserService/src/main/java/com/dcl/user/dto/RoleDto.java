package com.dcl.user.dto;


import com.dcl.user.RoleType;

import lombok.Data;

@Data
public class RoleDto {
     private Integer roleId;
	
	private RoleType roleName;

}
