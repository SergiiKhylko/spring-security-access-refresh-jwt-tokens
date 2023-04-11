package com.ajkko.userservice.service;

import com.ajkko.userservice.dto.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> getRoles();
    RoleDto saveRole(RoleDto role);
}
