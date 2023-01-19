package com.nik.auth.api.menu.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.nik.auth.api.menu.code.MenuSe;
import com.nik.auth.api.menu.code.MenuType;
import com.nik.auth.api.role.dto.RoleDTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class InheritRoleMenuDTO {

    private Long id;

    private MenuType menuType;

    private MenuSe menuSe;

    private int menuOrder;

    private String menuNm;

    private UpperMenuDTO upperMenu;

    private String menuPath;
    
    private String menuDesc;
    
    private boolean useYn;
    
    private boolean visibleYn;
    
    private boolean delYn;

    private Set<RoleDTO> roleList = new HashSet<>();

    private String depth;

    private List<InheritRoleMenuDTO> lowerMenu;
    
    private Set<RoleDTO> inheritRoleList = new HashSet<>();
    
}
