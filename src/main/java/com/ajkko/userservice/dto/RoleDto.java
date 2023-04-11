package com.ajkko.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto extends BaseDto {
    private String name;
    private String description;

    public RoleDto(Long id, String name, String description) {
        this.setId(id);
        this.name = name;
        this.description = description;
    }
}
