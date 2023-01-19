package com.nik.auth.api.role.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.auth.api.role.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>, RoleRepositoryDsl {


    /**
     * 권한이 중복인지 확인.
     * @param roleValue
     * @return
     */
    public boolean existsByRoleValue(String roleValue);

    @EntityGraph(attributePaths = {"privilegeList"})
    public Optional<Role> findById(Long id);

}
