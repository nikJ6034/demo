package com.nik.auth.api.menu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nik.auth.api.menu.dto.MenuDTO;
import com.nik.auth.api.menu.entity.Menu;

@Mapper(uses = UpperMenuMapper.class)
public interface MenuMapper {

    @Mapping(source = "regId.id", target = "regId")
    @Mapping(source = "mdfcnId.id", target = "mdfcnId")
    MenuDTO toDto(Menu menu);

    @Mapping(source = "regId", target = "regId.id")
    @Mapping(source = "mdfcnId", target = "mdfcnId.id")
    Menu toEntity(MenuDTO menuDTO);
}
