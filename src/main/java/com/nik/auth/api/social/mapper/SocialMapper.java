package com.nik.auth.api.social.mapper;

import org.mapstruct.Mapper;

import com.nik.auth.api.member.mapper.MemberMapper;
import com.nik.auth.api.role.mapper.RoleMapper;
import com.nik.auth.api.social.dto.SocialDTO;
import com.nik.auth.api.social.entity.Social;

@Mapper(uses = {RoleMapper.class, MemberMapper.class})
public interface SocialMapper {

    SocialDTO toDto(Social social);

    Social toEntity(SocialDTO socialDTO);
}
