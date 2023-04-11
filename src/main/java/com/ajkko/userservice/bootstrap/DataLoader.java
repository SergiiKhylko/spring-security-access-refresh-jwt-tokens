package com.ajkko.userservice.bootstrap;

import com.ajkko.userservice.dto.RoleDto;
import com.ajkko.userservice.dto.UserDto;
import com.ajkko.userservice.service.RoleService;
import com.ajkko.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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

        UserDto john = userService.saveUser(new UserDto("john", "John", "Travolta", new ArrayList<>()));
        UserDto will = userService.saveUser(new UserDto("will", "Will", "Smith", new ArrayList<>()));
        UserDto jim = userService.saveUser(new UserDto("jim", "Jim", "Carry",  new ArrayList<>()));
        UserDto arnold = userService.saveUser(new UserDto("arnold", "Arnold", "Schwarzenegger", new ArrayList<>()));

        userService.addRoleToUser(john.getId(), user.getId());
        userService.addRoleToUser(will.getId(), user.getId());
        userService.addRoleToUser(jim.getId(), user.getId());
        userService.addRoleToUser(arnold.getId(), user.getId());

        userService.addRoleToUser(john.getId(), manager.getId());
        userService.addRoleToUser(will.getId(), manager.getId());

        userService.addRoleToUser(jim.getId(), admin.getId());
        userService.addRoleToUser(arnold.getId(), admin.getId());
        userService.addRoleToUser(arnold.getId(), superAdmin.getId());
    }
}
