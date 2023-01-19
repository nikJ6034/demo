package com.nik.auth.api.role.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nik.auth.api.privilege.code.PrivRole;
import com.nik.auth.api.role.dto.RoleDTO;
import com.nik.auth.api.role.service.RoleService;
import com.nik.auth.common.ResultData;
import com.nik.auth.common.ResultType;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    //@PreAuthorize("@authorizationChecker.check(authentication,'ROLE_READ')")
    @PreAuthorize("hasAuthority('"+PrivRole.P_READ+"')")
    @GetMapping(value="/api/roles")
    public List<RoleDTO> selectRoleList(){
        return roleService.selectRoleList();
    }

    @PreAuthorize("hasAuthority('"+PrivRole.P_READ+"')")
    @GetMapping(value="/api/roles/{id}")
    public RoleDTO selectRole(@PathVariable Long id){
        return roleService.selectRole(id);
    }

    //@PreAuthorize("@authorizationChecker.check(authentication,'ROLE_WRITE')")
    @PreAuthorize("hasAuthority('"+PrivRole.P_WRITE+"')")
    @PostMapping(value="/api/roles")
    public ResultData<RoleDTO> insertRole(@RequestBody @Valid RoleDTO roleDTO){

        boolean existsByRoleValue = roleService.existsByRoleValue(roleDTO.getRoleValue());
        ResultData<RoleDTO> resultData = new ResultData<>();

        if(existsByRoleValue) {
            resultData.setMsg("권한이 이미 존재합니다.");
            resultData.setResultType(ResultType.FAIL);

        }else {
            
            resultData.setMsg("등록되었습니다.");
            resultData.setResultType(ResultType.SUCCESS);
            resultData.setData(roleService.insertRole(roleDTO));

        }

        return resultData;
    }

    //@PreAuthorize("@authorizationChecker.check(authentication,'ROLE_MODIFY')")
    @PreAuthorize("hasAuthority("+PrivRole.P_MODIFY+")")
    @PutMapping(value="/api/roles")
    public ResultData<RoleDTO> modifyRole(@RequestBody @Valid RoleDTO roleDTO){

        ResultData<RoleDTO> resultData = new ResultData<>();

        resultData.setMsg("수정되었습니다.");

        resultData.setResultType(ResultType.SUCCESS);
        resultData.setData(roleService.modifyRole(roleDTO));

        return resultData;
    }

    // @PreAuthorize("@authorizationChecker.check(authentication,'ROLE_DELETE')")
    @PreAuthorize("hasAuthority("+PrivRole.P_DELETE+")")
    @DeleteMapping(value="/api/roles/{id}")
    public ResultData<RoleDTO> deleteRole(@PathVariable Long id){
        ResultData<RoleDTO> resultData = new ResultData<>();

        // List<PrivilegeVO> selectPrivilegeListByRoleId = privilegeService.selectPrivilegeListByRoleId(id);

        // if(selectPrivilegeListByRoleId.isEmpty()) {
        //     roleService.deleteRole(id);
        //     resultData.setResultType(ResultType.SUCCESS);
        //     resultData.setMsg("삭제되었습니다.");
        // }else {
        //     resultData.setResultType(ResultType.FAIL);
        //     resultData.setMsg("설정된 업무권한이 존재합니다. 업무권한을 삭제해주세요.");
        // }

        return resultData;
    }

    //@PreAuthorize("hasAuthority('"+PrivRole.Privilege.WRITE+"')")
    @PostMapping(value="/api/roles/{id}/privileges")
    public ResultData<RoleDTO> modifyRolePrivileges(@PathVariable Long id, @RequestBody @Valid Set<String> privilegeList){

        ResultData<RoleDTO> resultData = new ResultData<>();
            
        resultData.setMsg("등록되었습니다.");
        resultData.setResultType(ResultType.SUCCESS);
        resultData.setData(roleService.modifyRolePrivileges(id, privilegeList));

        return resultData;
    }

}
