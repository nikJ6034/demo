package com.nik.auth.api.role.repository;

import java.util.List;

import com.nik.auth.api.role.dto.RoleDTO;

public interface RoleRepositoryDsl {

    public List<RoleDTO> selectRoleList();
}
