package com.nik.auth.api.role.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RoleDTO implements GrantedAuthority{
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "권한을 입력해주세요.")
    private String roleValue;

    @NotEmpty(message = "권한 이름을 입력해주세요.")
    private String roleName;

    private Set<String> privilegeList;

    @JsonIgnore
    @Override
    public String getAuthority() {
        return roleValue;
    }

    @Getter @Setter
    public static class WithoutPrivilegeDTO {
        private Long id;

        private String roleValue;

        private String roleName;
    }

}
