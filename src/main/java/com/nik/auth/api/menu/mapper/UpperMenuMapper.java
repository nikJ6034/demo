package com.nik.auth.api.menu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nik.auth.api.menu.dto.UpperMenuDTO;
import com.nik.auth.api.menu.entity.Menu;

@Mapper
public interface UpperMenuMapper {

    UpperMenuDTO toDto(Menu menu);

    @Mapping(target = "delYn", ignore = true)
    @Mapping(target = "lowerMenu", ignore = true)
    @Mapping(target = "mdfcnId", ignore = true)
    @Mapping(target = "mdfcnDt", ignore = true)
    @Mapping(target = "menuDesc", ignore = true)
    @Mapping(target = "regId", ignore = true)
    @Mapping(target = "regDt", ignore = true)
    @Mapping(target = "roleList", ignore = true)
    @Mapping(target = "upperMenu", ignore = true)
    @Mapping(target = "useYn", ignore = true)
    @Mapping(target = "visibleYn", ignore = true)
    Menu toEntity(UpperMenuDTO upperMenuDTO);
}
