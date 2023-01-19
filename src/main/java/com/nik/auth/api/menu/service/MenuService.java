package com.nik.auth.api.menu.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nik.auth.api.menu.code.MenuType;
import com.nik.auth.api.menu.dto.InheritRoleMenuDTO;
import com.nik.auth.api.menu.dto.MenuDTO;
import com.nik.auth.api.menu.entity.Menu;
import com.nik.auth.api.menu.mapper.MenuMapper;
import com.nik.auth.api.menu.repository.MenuRepository;
import com.nik.auth.api.role.dto.RoleDTO;
import com.nik.auth.common.ResultData;
import com.nik.auth.common.ResultType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuService {

    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    @CacheEvict(value = "menu", allEntries = true) //해당 메소드가 실행되면 menu로 설정된 값의 캐싱이 전부 삭제됩니다.
    public void insertMenu(MenuDTO menuDTO) {

        Menu menu = menuMapper.toEntity(menuDTO);
        menu.setRegDt(LocalDateTime.now());
        menuRepository.save(menu);

    }

//    @Cacheable(value="menu", key="#menuId") //해당 메소드가 실행되면 menu로 값을 캐싱합니다. 재 실행시 캐시에 등록된 데이터를 리턴합니다.
//    public MenuVO selectMenu(Long menuId){
//
//        MenuVO selectMenu = menuRepository.selectMenu(menuId);
//
//        selectMenu(selectMenu, false);
//
//        return selectMenu;
//
//    }

    @Cacheable(value="menu", key="#menuType") //해당 메소드가 실행되면 menu로 값을 캐싱합니다. 재 실행시 캐시에 등록된 데이터를 리턴합니다.
    public List<InheritRoleMenuDTO> select1StMenu(MenuType menuType){

        List<InheritRoleMenuDTO> selectMenu = menuRepository.select1StMenu(menuType);
        if(selectMenu != null && !selectMenu.isEmpty()) {
            for(InheritRoleMenuDTO menu : selectMenu) {
                Set<RoleDTO> selectMenuRole = menuRepository.selectMenuRole(menu.getId());
                menu.setRoleList(selectMenuRole);
                menu.setDepth("|"+menu.getId()+"|");
                selectMenu(menu);
            }
        }

        return selectMenu;

    }

    /**
     * 재귀 호출용 함수
     * @param menuVO
     * @return
     */
    private void selectMenu(InheritRoleMenuDTO inheritRoleMenuDTO) {

        if(inheritRoleMenuDTO.getLowerMenu()!= null && !inheritRoleMenuDTO.getLowerMenu().isEmpty()) {

            for(InheritRoleMenuDTO menu : inheritRoleMenuDTO.getLowerMenu()) {
                InheritRoleMenuDTO selectMenu = menuRepository.selectMenu(menu.getId());
                if(selectMenu != null) {
                    Set<RoleDTO> selectMenuRole = menuRepository.selectMenuRole(menu.getId());
                    Set<RoleDTO> mergedSet = new HashSet<>();
                    mergedSet.addAll(inheritRoleMenuDTO.getRoleList());
                    mergedSet.addAll(inheritRoleMenuDTO.getInheritRoleList());

                    menu.setDepth(inheritRoleMenuDTO.getDepth()+menu.getId()+"|");
                    menu.setRoleList(selectMenuRole);
                    menu.setInheritRoleList(mergedSet);
                    menu.setLowerMenu(selectMenu.getLowerMenu());
                    menu.setUpperMenu(selectMenu.getUpperMenu());

                    selectMenu(menu);
                }
            }
        }
    }

    @CacheEvict(value = "menu", allEntries = true) //해당 메소드가 실행되면 menu로 설정된 값의 캐싱이 전부 삭제됩니다.
    public ResultData<Void> updateMenu(MenuDTO menuDTO) {

        Menu menu = menuMapper.toEntity(menuDTO);
        menu.setMdfcnDt(LocalDateTime.now());

        ResultData<Void> resultData = new ResultData<>();

        Optional<Menu> findById = menuRepository.findById(menu.getId());

        if(menu.getUpperMenu() != null && menu.getId().equals(menu.getUpperMenu().getId())) {
            resultData.setMsg("자신을 상위 메뉴로 지정할 수 없습니다.");
            resultData.setResultType(ResultType.FAIL);
            return resultData;
        }

        if(findById.isPresent()) {
            Menu menu2 = findById.get();

            menu2.setMenuSe(menu.getMenuSe());
            menu2.setMenuOrder(menu.getMenuOrder());
            menu2.setMenuNm(menu.getMenuNm());
            menu2.setUpperMenu(menu.getUpperMenu());
            menu2.setRoleList(menu.getRoleList());
            menu2.setMenuPath(menu.getMenuPath());
            menu2.setMenuDesc(menu.getMenuDesc());
            menu2.setUseYn(menu.isUseYn());
//            menu_.setVisibleYn(menu.isVisibleYn());
            menu2.setMdfcnId(menu.getMdfcnId());
            menu2.setMdfcnDt(menu.getMdfcnDt());

            resultData.setMsg("수정되었습니다.");
            resultData.setResultType(ResultType.SUCCESS);
            return resultData;
        }

        resultData.setMsg("존해지 않는 데이터입니다.");
        resultData.setResultType(ResultType.FAIL);
        return resultData;

    }

    @CacheEvict(value = "menu", allEntries = true) //해당 메소드가 실행되면 menu로 설정된 값의 캐싱이 전부 삭제됩니다.
    public void deleteMenu(MenuDTO menuDTO) {
        Menu menu = menuMapper.toEntity(menuDTO);
        menu.setMdfcnDt(LocalDateTime.now());
        
        Optional<Menu> findById = menuRepository.findById(menu.getId());

        if(findById.isPresent()) {
            Menu menu2 = findById.get();
            menu2.setDelYn(true);
            menu2.setMdfcnId(menu.getMdfcnId());
            menu2.setMdfcnDt(menu.getMdfcnDt());
        }

    }

    public boolean existsChildrenMenu(Long menuId) {
        return menuRepository.existsChildrenMenu(menuId);
    }

    @CacheEvict(value = "menu", allEntries = true)
    public void updateMenuOrder(List<MenuDTO> menuList) {

        for(int i = 0; i < menuList.size(); i++ ){
            menuRepository.updateMenuOrder(menuList.get(i), i+1);
        }

    }

}
