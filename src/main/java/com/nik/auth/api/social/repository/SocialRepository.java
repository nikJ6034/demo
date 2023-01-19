package com.nik.auth.api.social.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.auth.api.social.entity.Social;

public interface SocialRepository extends JpaRepository<Social, Long> {

    @EntityGraph(attributePaths = {"member.memberRoleList.role"})
    public Social findByClientId(String clientId);

}
