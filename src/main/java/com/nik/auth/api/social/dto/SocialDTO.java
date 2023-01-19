package com.nik.auth.api.social.dto;

import com.nik.auth.api.member.dto.MemberDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SocialDTO {
    
    private Long id;

    private String clientId;

    private String clientName;

    private String uuid;

    private MemberDTO member;
}
