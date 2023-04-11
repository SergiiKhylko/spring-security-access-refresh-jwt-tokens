package com.ajkko.userservice.mapper;

import com.ajkko.userservice.dto.RoleDto;
import com.ajkko.userservice.entity.Role;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMapper implements Mapper<RoleDto, Role>{

    @Override
    public Role mapToEntity(RoleDto dto) {
        return dto == null ? null
                : new Role(
                dto.getId(),
                dto.getName(),
                dto.getDescription()
        );
    }

    @Override
    public RoleDto mapToDto(Role entity) {
        return entity == null ? null
                : new RoleDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription()
        );
    }

    @Override
    public List<Role> mapToEntities(Collection<RoleDto> dtos) {
        return dtos == null ? null
                : dtos.stream().map(this::mapToEntity).collect(Collectors.toList());
    }

    @Override
    public List<RoleDto> mapToDtos(Collection<Role> entities) {
        return entities == null ? null
                : entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}
