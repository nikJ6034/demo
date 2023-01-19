package com.nik.auth.api.rolegroup.dto;

import java.util.List;

import com.nik.auth.api.role.dto.RoleDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class RoleGroupDTO {
    
    private Long id;

    private String groupName;

    private String groupDesc;

    private List<RoleDTO> roleList;

    
    @Getter @Setter @ToString
    public static class WithoutPrivilegeDTO {
        private Long id;

        private String groupName;

        private String groupDesc;

        private List<RoleDTO.WithoutPrivilegeDTO> roleList;
    }
}
