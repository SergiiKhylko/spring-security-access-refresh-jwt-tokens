package com.ajkko.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {
    private String name;
    private String description;

    public Role(Long id, String name, String description) {
        this.setId(id);
        this.name = name;
        this.description = description;
    }
}
