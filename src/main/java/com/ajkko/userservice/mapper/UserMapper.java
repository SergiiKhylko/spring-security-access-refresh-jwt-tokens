package com.ajkko.userservice.mapper;

import com.ajkko.userservice.dto.UserDto;
import com.ajkko.userservice.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<UserDto, User>{

    private final RoleMapper roleMapper;
    private final PasswordEncoder passwordEncoder;

    public User mapToEntity(UserDto dto) {
        return dto == null ? null
                : new User(
                dto.getId(),
                dto.getUsername(),
                dto.getFirstName(),
                dto.getLastName(),
                passwordEncoder.encode(dto.getPassword()),
                dto.getRoles().stream().
                        map(roleMapper::mapToEntity).collect(Collectors.toList())
        );
    }

    public UserDto mapToDto(User entity) {
        return entity == null ? null
                : new UserDto(
                    entity.getId(),
                    entity.getUsername(),
                    entity.getFirstName(),
                    entity.getLastName(),
                    entity.getRoles().stream().
                    map(roleMapper::mapToDto).collect(Collectors.toList())
                );
    }

    @Override
    public List<User> mapToEntities(Collection<UserDto> dtos) {
        return dtos == null ? null
                : dtos.stream().map(this::mapToEntity).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> mapToDtos(Collection<User> entities) {
        return entities == null ? null
                : entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}
