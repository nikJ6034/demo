package com.nik.auth.api.rolegroup.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.nik.auth.api.role.mapper.RoleMapper;
import com.nik.auth.api.rolegroup.dto.RoleGroupDTO;
import com.nik.auth.api.rolegroup.entity.RoleGroup;

@Mapper(uses = RoleMapper.class)
public interface RoleGroupMapper {

    RoleGroupDTO toDto(RoleGroup roleGroup);

    RoleGroup toEntity(RoleGroupDTO roleGroupDTO);

    //@Mapping(source = "roleList", target = "roleList", qualifiedByName = "toWithoutPrivilegeDto")
    RoleGroupDTO.WithoutPrivilegeDTO toWithoutPrivilegeDto(RoleGroup roleGroup);

    List<RoleGroupDTO.WithoutPrivilegeDTO> toWithoutPrivilegeDto(List<RoleGroup> roleGroup);

    //@Mapping(target = "RoleGroupDTO.privilegeList", ignore = true)
    RoleGroup toWithoutPrivilegeEntity(RoleGroupDTO.WithoutPrivilegeDTO roleGroupDTO);
}
