package com.ajkko.userservice.api;

import com.ajkko.userservice.dto.RoleDto;
import com.ajkko.userservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDto>> getRoles() {
        return ResponseEntity.ok(roleService.getRoles());
    }

    @PostMapping
    public ResponseEntity<RoleDto> saveRole(@RequestBody RoleDto role) {
        RoleDto createdRole = roleService.saveRole(role);
        String id = createdRole.getId().toString();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(id).build().toUri();
        return ResponseEntity.created(location).body(createdRole);
    }
}

