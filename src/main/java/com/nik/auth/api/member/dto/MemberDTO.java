package com.nik.auth.api.member.dto;

import com.nik.auth.api.rolegroup.dto.RoleGroupDTO;

import lombok.Data;

@Data
public class MemberDTO {
    private Long id;

//  @NotEmpty(message = "아이디를 입력해주세요.")
    private String memberId;

//  @NotEmpty(message = "회원이름을 입력해주세요.")
    private String name;

//  @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String memberPassword;

//  @Email
    private String email;

    private String mobile;

    private boolean delYn;

    private RoleGroupDTO roleGroup;
}
