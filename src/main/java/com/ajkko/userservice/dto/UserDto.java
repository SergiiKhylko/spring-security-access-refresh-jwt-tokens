package com.ajkko.userservice.dto;

import com.ajkko.userservice.entity.Role;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {

    private String username;
    private String firstName;
    private String lastName;

    private List<RoleDto> roles = new ArrayList<>();

    public UserDto(Long id, String username, String firstName, String lastName, List<RoleDto> roles) {
        this.setId(id);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }
}
