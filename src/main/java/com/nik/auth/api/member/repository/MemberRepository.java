package com.nik.auth.api.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.auth.api.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryDsl{

}
