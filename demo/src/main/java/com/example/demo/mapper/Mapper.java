package com.example.demo.mapper;

import com.example.demo.dto.AbstractDto;
import com.example.demo.entities.AbstractEntity;

public interface Mapper<ENTITY extends AbstractEntity, DTO extends AbstractDto> {
    ENTITY toEntity(DTO dto);

    DTO toDto(ENTITY entity);
}
