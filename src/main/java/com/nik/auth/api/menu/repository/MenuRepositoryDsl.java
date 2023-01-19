package com.nik.auth.api.menu.repository;

import java.util.List;
import java.util.Set;

import com.nik.auth.api.menu.code.MenuType;
import com.nik.auth.api.menu.dto.InheritRoleMenuDTO;
import com.nik.auth.api.menu.dto.MenuDTO;
import com.nik.auth.api.role.dto.RoleDTO;

public interface MenuRepositoryDsl {

    public InheritRoleMenuDTO selectMenu(Long menuId);

    public List<InheritRoleMenuDTO> select1StMenu(MenuType menuType);

    public boolean existsChildrenMenu(Long menuId);

    public Set<RoleDTO> selectMenuRole(Long menuId);

    public void updateMenuOrder(MenuDTO menu, int i);

}
