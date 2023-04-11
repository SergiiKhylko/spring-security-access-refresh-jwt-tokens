package com.ajkko.userservice.bootstrap;

import com.ajkko.userservice.dto.RoleDto;
import com.ajkko.userservice.dto.UserDto;
import com.ajkko.userservice.service.RoleService;
import com.ajkko.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;

    @Override
    public void run(String... args) throws Exception {

        if (roleService.getRoles().isEmpty() || userService.getUsers().isEmpty()){
            loadData();
        }
    }

    private void loadData() {
        RoleDto user = roleService.saveRole(new RoleDto("USER", "User role"));
        RoleDto manager = roleService.saveRole(new RoleDto("MANAGER", "Manager role"));
        RoleDto admin = roleService.saveRole(new RoleDto("ADMIN", "Admin role"));
        RoleDto superAdmin = roleService.saveRole(new RoleDto("SUPER_ADMIN", "Super admin role"));

        userService.saveUser(new UserDto("john", "John", "Travolta", "p@ssw0rd", List.of(user, manager)));
        userService.saveUser(new UserDto("will", "Will", "Smith", "p@ssw0rd", List.of(user, manager)));
        userService.saveUser(new UserDto("jim", "Jim", "Carry", "p@ssw0rd", List.of(user, admin)));
        userService.saveUser(new UserDto("arnold", "Arnold", "Schwarzenegger", "p@ssw0rd", List.of(user, admin, superAdmin)));
    }
}
