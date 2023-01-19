package com.nik.auth.api.rolegroup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.auth.api.rolegroup.entity.RoleGroup;

public interface RoleGroupRepository extends JpaRepository<RoleGroup, Long>{
    
    @EntityGraph(attributePaths = {"roleList"})
    List<RoleGroup> findAll();

    @EntityGraph(attributePaths = {"roleList"})
    Optional<RoleGroup> findById(Long id);
}
