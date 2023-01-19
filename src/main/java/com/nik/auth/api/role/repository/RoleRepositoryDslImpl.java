package com.nik.auth.api.role.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.nik.auth.api.role.dto.RoleDTO;
import com.nik.auth.api.role.entity.QRole;
import com.nik.auth.api.role.entity.Role;
import com.querydsl.core.types.Projections;

public class RoleRepositoryDslImpl extends QuerydslRepositorySupport implements RoleRepositoryDsl{

    public RoleRepositoryDslImpl() {
        super(Role.class);
    }

    @Override
    public List<RoleDTO> selectRoleList() {

        QRole role = QRole.role;

        return from(role)
        .select(Projections.bean(RoleDTO.class,
                role.id,
                role.roleName,
                role.roleValue
                ))
        .where(role.id.ne(1L)) // 아이디가 1번인 경우 슈퍼 계정이어야합니다. 그리고 1번은 리스트에서 조회되면 안됩니다.
        .fetch();
    }



}
