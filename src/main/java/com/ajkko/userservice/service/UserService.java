package com.ajkko.userservice.service;

import com.ajkko.userservice.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto user);
    UserDto addRoleToUser(Long userId, Long roleId);
    UserDto getUser(String username);
    UserDto getUser(Long userId);
    List<UserDto> getUsers();
}
