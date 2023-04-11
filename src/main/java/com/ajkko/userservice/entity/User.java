package com.ajkko.userservice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    private String username;
    private String firstName;
    private String lastName;
    private String password;

    public User(Long id, String username, String firstName, String lastName, String password, Collection<Role> roles) {
        this.setId(id);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.roles = roles;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

}
