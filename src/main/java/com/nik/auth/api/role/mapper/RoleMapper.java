package com.nik.auth.api.role.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.nik.auth.api.role.dto.RoleDTO;
import com.nik.auth.api.role.entity.Role;

@Mapper
public interface RoleMapper {

    RoleDTO toDto(Role role);

    Role toEntity(RoleDTO roleDTO);

    @Named("toWithoutPrivilegeDto")
    RoleDTO.WithoutPrivilegeDTO toWithoutPrivilegeDto(Role role);

    @Mapping(target="privilegeList", ignore = true)
    Role toWithoutPrivilegeEntity(RoleDTO.WithoutPrivilegeDTO roleDTO);

    List<Role> toWithoutPrivilegeEntity(List<RoleDTO.WithoutPrivilegeDTO> roleDTOList);

}
