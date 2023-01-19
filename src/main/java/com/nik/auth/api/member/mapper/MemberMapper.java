package com.nik.auth.api.member.mapper;

import org.mapstruct.Mapper;

import com.nik.auth.api.member.dto.MemberDTO;
import com.nik.auth.api.member.entity.Member;

@Mapper
public interface MemberMapper {

    MemberDTO toDto(Member member);
    
    Member toEntity(MemberDTO member);

}
