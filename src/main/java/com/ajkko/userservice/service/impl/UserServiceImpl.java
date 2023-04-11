package com.ajkko.userservice.service.impl;

import com.ajkko.userservice.dto.UserDto;
import com.ajkko.userservice.entity.Role;
import com.ajkko.userservice.entity.User;
import com.ajkko.userservice.exception.RoleNotFoundException;
import com.ajkko.userservice.exception.UserNotFoundException;
import com.ajkko.userservice.mapper.UserMapper;
import com.ajkko.userservice.repository.RoleRepository;
import com.ajkko.userservice.repository.UserRepository;
import com.ajkko.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    @Override
    public UserDto saveUser(UserDto user) {
        log.info("Saving new user {} to the database", user.getUsername());
        User savedUser = userRepository.save(userMapper.mapToEntity(user));
        return userMapper.mapToDto(savedUser);
    }

    @Override
    public UserDto addRoleToUser(Long userId, Long roleId) {
        log.info("Adding role {} to user {}", roleId, userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role with id " + roleId + " not found"));

        Collection<Role> roles = user.getRoles();
        if (!roles.contains(role)) {
            roles.add(role);
        }

        User savedUser = userRepository.save(user);
        return userMapper.mapToDto(savedUser);
    }

    @Override
    public UserDto getUser(String username) {
        log.info("Fetching user {}", username);
        User foundUser = userRepository.findByUsername(username);
        return userMapper.mapToDto(foundUser);
    }

    @Override
    public UserDto getUser(Long id) {
        log.info("Fetching user with id {}", id);
        User foundUser = userRepository.findById(id).orElse(null);
        return userMapper.mapToDto(foundUser);
    }

    @Override
    public List<UserDto> getUsers() {
        log.info("Fetching all users");
        List<User> foundUsers = userRepository.findAll();
        return userMapper.mapToDtos(foundUsers);
    }
}
