package com.nik.auth.api.menu.repository;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.nik.auth.api.menu.code.MenuType;
import com.nik.auth.api.menu.dto.InheritRoleMenuDTO;
import com.nik.auth.api.menu.dto.MenuDTO;
import com.nik.auth.api.menu.dto.UpperMenuDTO;
import com.nik.auth.api.menu.entity.Menu;
import com.nik.auth.api.menu.entity.QMenu;
import com.nik.auth.api.role.dto.RoleDTO;
import com.nik.auth.api.role.entity.QRole;
import com.querydsl.core.types.Projections;

public class MenuRepositoryDslImpl extends QuerydslRepositorySupport implements MenuRepositoryDsl{

    public MenuRepositoryDslImpl() {
        super(Menu.class);
    }

    @Override
    public InheritRoleMenuDTO selectMenu(Long menuId) {

        QMenu menu = new QMenu("menu");
        QMenu pMenu = new QMenu("pMenu");
        QMenu cMenu = new QMenu("cMenu");

        return from(menu)
        .leftJoin(menu.upperMenu, pMenu)
        .on(pMenu.delYn.eq(false))
        .leftJoin(menu.lowerMenu, cMenu)
        .on(cMenu.delYn.eq(false))
        .where(menu.id.eq(menuId), menu.delYn.eq(false))
        .orderBy(menu.menuOrder.asc(), cMenu.menuOrder.asc())
        .transform(groupBy(menu.id).as(
                Projections.bean(InheritRoleMenuDTO.class,
                        menu.id,
                        menu.menuType,
                        menu.menuSe,
                        menu.menuOrder,
                        menu.menuNm,
                        menu.menuPath,
                        menu.menuDesc,
                        menu.useYn,
                        menu.visibleYn,
                        menu.delYn,
                        Projections.bean(UpperMenuDTO.class,
                                pMenu.id,
                                pMenu.menuType,
                                pMenu.menuSe,
                                pMenu.menuOrder,
                                pMenu.menuNm,
                                pMenu.menuPath
                                )
                            .as("upperMenu"),
                        list(Projections.bean(InheritRoleMenuDTO.class,
                                cMenu.id,
                                cMenu.menuType,
                                cMenu.menuSe,
                                cMenu.menuOrder,
                                cMenu.menuNm,
                                cMenu.menuPath,
                                cMenu.menuDesc,
                                cMenu.useYn,
                                cMenu.visibleYn,
                                cMenu.delYn
                                ).skipNulls()).as("lowerMenu")
                        )
                ))
        .get(menuId);

    }

    @Override
    public List<InheritRoleMenuDTO> select1StMenu(MenuType menuType) {

        QMenu menu = new QMenu("menu");
        QMenu pMenu = new QMenu("pMenu");
        QMenu cMenu = new QMenu("cMenu");

        return from(menu)
        .leftJoin(menu.upperMenu, pMenu)
        .on(pMenu.delYn.eq(false))
        .leftJoin(menu.lowerMenu, cMenu)
        .on(cMenu.delYn.eq(false))
        .where(menu.menuType.eq(menuType),menu.upperMenu.id.isNull(), menu.delYn.eq(false))
        .orderBy(menu.menuOrder.asc(), cMenu.menuOrder.asc())
        .transform(groupBy(menu.id, menu.menuType,menu.upperMenu.id.isNull()).list(
                Projections.bean(InheritRoleMenuDTO.class,
                        menu.id,
                        menu.menuType,
                        menu.menuSe,
                        menu.menuOrder,
                        menu.menuNm,
                        menu.menuPath,
                        menu.menuDesc,
                        menu.useYn,
                        menu.visibleYn,
                        menu.delYn,
                        Projections.bean(UpperMenuDTO.class,
                                pMenu.id,
                                pMenu.menuType,
                                pMenu.menuSe,
                                pMenu.menuOrder,
                                pMenu.menuNm,
                                pMenu.menuPath
                                )
                            .as("upperMenu"),
                        list(Projections.bean(InheritRoleMenuDTO.class,
                                cMenu.id,
                                cMenu.menuType,
                                cMenu.menuSe,
                                cMenu.menuOrder,
                                cMenu.menuNm,
                                cMenu.menuPath,
                                cMenu.menuDesc,
                                cMenu.useYn,
                                cMenu.visibleYn,
                                cMenu.delYn
                                ).skipNulls()).as("lowerMenu")
                        )
                ));

    }

    @Override
    public boolean existsChildrenMenu(Long menuId) {
        QMenu menu = new QMenu("menu");

        return from(menu)
        .where(menu.upperMenu.id.eq(menuId), menu.delYn.eq(false))
        .fetchCount() > 1;
    }

    @Override
    public Set<RoleDTO> selectMenuRole(Long menuId) {
        QMenu menu = new QMenu("menu");
        QRole role = new QRole("role");

        List<RoleDTO> fetch = from(menu)
        .join(menu.roleList, role)
        .where(menu.id.eq(menuId))
        .select(Projections.bean(RoleDTO.class,
                role.id,
                role.roleName,
                role.roleValue
                ))
        .fetch();

        return new HashSet<>(fetch);
    }

    @Override
    public void updateMenuOrder(MenuDTO menu, int order) {
        QMenu qMenu = new QMenu("menu");

        update(qMenu)
        .set(qMenu.menuOrder, order)
        .where(qMenu.id.eq(menu.getId()))
        .execute();
        
    }

}
