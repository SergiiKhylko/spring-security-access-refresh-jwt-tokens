package com.ajkko.userservice.service.impl;

import com.ajkko.userservice.dto.RoleDto;
import com.ajkko.userservice.entity.Role;
import com.ajkko.userservice.mapper.RoleMapper;
import com.ajkko.userservice.repository.RoleRepository;
import com.ajkko.userservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public List<RoleDto> getRoles() {
        log.info("Fetching all roles");
        List<Role> roles = roleRepository.findAll();
        return roleMapper.mapToDtos(roles);
    }

    @Override
    public RoleDto saveRole(RoleDto roleDto) {
        log.info("Saving new role {} to the database", roleDto.getName());
        Role role = roleMapper.mapToEntity(roleDto);
        Role savedRole = roleRepository.save(role);
        return roleMapper.mapToDto(savedRole);
    }
}
