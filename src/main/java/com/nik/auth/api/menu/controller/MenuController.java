package com.nik.auth.api.menu.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nik.auth.api.menu.code.MenuSe;
import com.nik.auth.api.menu.code.MenuType;
import com.nik.auth.api.menu.dto.InheritRoleMenuDTO;
import com.nik.auth.api.menu.dto.MenuDTO;
import com.nik.auth.api.menu.service.MenuService;
import com.nik.auth.api.privilege.code.PrivMenu;
import com.nik.auth.common.ResultData;
import com.nik.auth.common.ResultType;
import com.nik.auth.security.dto.PrincipalDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping(value = "/api/menuType")
    public List<Map<String, String>> selectMenuTypes() {
        //@JsonFormat(shape = Shape.OBJECT) 사용 금지.
        return Arrays.asList(MenuType.values()).stream()
        .map(mt -> {
            Map<String, String> map = new HashMap<>();
            map.put("key", mt.toString());
            map.put("name", mt.getName());
            return map;
        })
        .collect(Collectors.toList());
    }

    @GetMapping(value = "/api/menuSe")
    public List<Map<String, String>> selectMenuSe() {
        //@JsonFormat(shape = Shape.OBJECT) 사용 금지.
        return Arrays.asList(MenuSe.values()).stream()
        .map(mt -> {
            Map<String, String> map = new HashMap<>();
            map.put("key", mt.toString());
            map.put("name", mt.getName());
            return map;
        })
        .collect(Collectors.toList());
    }

    @GetMapping(value = "/api/menus")
    public List<InheritRoleMenuDTO> selectMenu(MenuType menuType) {
        return menuService.select1StMenu(menuType);
    }

    //@PreAuthorize("@authorizationChecker.check(authentication,'MENU_WRITE')")
    @PreAuthorize("hasAuthority("+PrivMenu.P_WRITE+")")
    @PostMapping(value = "/api/menus")
    public ResultData<Void> insertMenu(@AuthenticationPrincipal PrincipalDetails principalDetails, @Valid @RequestBody MenuDTO menuDTO) {
        ResultData<Void> resultData = new ResultData<>();
        menuDTO.setRegId(principalDetails.getId());
        menuService.insertMenu(menuDTO);

        resultData.setMsg("등록되었습니다.");
        resultData.setResultType(ResultType.SUCCESS);

        return resultData;
    }

    //@PreAuthorize("@authorizationChecker.check(authentication,'MENU_MODIFY')")
    @PreAuthorize("hasAuthority("+PrivMenu.P_MODIFY+")")
    @PutMapping(value = "/api/menus")
    public ResultData<Void> updateMenu(@AuthenticationPrincipal PrincipalDetails principalDetails, @Valid @RequestBody MenuDTO menuDTO) {
        menuDTO.setMdfcnId(principalDetails.getId());
        return menuService.updateMenu(menuDTO);

    }

    //@PreAuthorize("@authorizationChecker.check(authentication,'MENU_DELETE')")
    @PreAuthorize("hasAuthority("+PrivMenu.P_DELETE+")")
    @DeleteMapping(value = "/api/menus/{id}")
    public ResultData<Void> deleteMenu(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long id) {
        ResultData<Void> resultData = new ResultData<>();
        if(menuService.existsChildrenMenu(id)) {
            resultData.setMsg("하위 메뉴가 존재합니다.");
            resultData.setResultType(ResultType.FAIL);
            return resultData;
        }

        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setId(id);
        menuDTO.setMdfcnId(principalDetails.getId());

        resultData.setMsg("삭제되었습니다.");
        resultData.setResultType(ResultType.SUCCESS);
        menuService.deleteMenu(menuDTO);

        return resultData;
    }

    // @PreAuthorize("@authorizationChecker.check(authentication,'MENU_MODIFY')")
    @PreAuthorize("hasAuthority("+PrivMenu.P_MODIFY+")")
    @PutMapping(value = "/api/menus/order")
    public ResultData<Void> updateMenuOrder(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody List<MenuDTO> menuList) {

        ResultData<Void> resultData = new ResultData<>();
        menuService.updateMenuOrder(menuList);

        resultData.setMsg("수정되었습니다.");
        resultData.setResultType(ResultType.SUCCESS);

        return resultData;

    }

}
