package com.ajkko.userservice.mapper;

import com.ajkko.userservice.dto.BaseDto;
import com.ajkko.userservice.entity.BaseEntity;

import java.util.Collection;
import java.util.List;

public interface Mapper<DTO extends BaseDto, ENT extends BaseEntity> {
    ENT mapToEntity(DTO dto);

    DTO mapToDto(ENT entity);

    List<ENT> mapToEntities(Collection<DTO> dtos);

    List<DTO> mapToDtos(Collection<ENT> entities);
}
