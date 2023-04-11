package com.ajkko.userservice.api;

import com.ajkko.userservice.dto.UserDto;
import com.ajkko.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        UserDto user = userService.getUser(id);
        return user == null
                ?   ResponseEntity.notFound().build()
                :   ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto user) {
        UserDto createdUser = userService.saveUser(user);
        String id = createdUser.getId().toString();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(id).build().toUri();
        return ResponseEntity.created(location).body(createdUser);
    }

    @PostMapping("{userId}/roles/{roleId}")
    public ResponseEntity<UserDto> addRole(@PathVariable Long userId,
                                        @PathVariable Long roleId) {
        try {
            return ResponseEntity.ok().body(
                    userService.addRoleToUser(userId, roleId)
            );
        } catch (EntityNotFoundException e) {
            log.error("resource not found error during addRole operation", e);
            return ResponseEntity.notFound().build();
        }
    }
}
