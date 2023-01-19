package com.nik.auth.api.rolegroup.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nik.auth.api.rolegroup.dto.RoleGroupDTO;
import com.nik.auth.api.rolegroup.service.RoleGroupService;
import com.nik.auth.common.ResultData;
import com.nik.auth.common.ResultType;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RoleGroupController {
    
    private final RoleGroupService roleGroupService;

    @GetMapping(value="/api/roleGroups")
    public ResponseEntity<List<RoleGroupDTO.WithoutPrivilegeDTO>> getRoleGroups(){

        List<RoleGroupDTO.WithoutPrivilegeDTO> roleGroups = roleGroupService.getRoleGroups();

        return ResponseEntity.ok()
        .body(roleGroups);
    }

    @PostMapping(value="/api/roleGroups")
    public ResultData<RoleGroupDTO.WithoutPrivilegeDTO> insertRoleGroup(@RequestBody RoleGroupDTO.WithoutPrivilegeDTO roleGroupDTO){
        ResultData<RoleGroupDTO.WithoutPrivilegeDTO> resultData = new ResultData<>();
        RoleGroupDTO.WithoutPrivilegeDTO roleGroup = roleGroupService.insertRoleGroup(roleGroupDTO);
        resultData.setData(roleGroup);
        resultData.setMsg("등록되었습니다.");
        resultData.setResultType(ResultType.SUCCESS);
        return resultData;
    }

    @PutMapping(value="/api/roleGroups")
    public ResultData<RoleGroupDTO.WithoutPrivilegeDTO> modifyRoleGroup(@RequestBody RoleGroupDTO.WithoutPrivilegeDTO roleGroupDTO){
        ResultData<RoleGroupDTO.WithoutPrivilegeDTO> resultData = new ResultData<>();
        RoleGroupDTO.WithoutPrivilegeDTO roleGroup = roleGroupService.modifyRoleGroup(roleGroupDTO);
        if(roleGroup != null){
            resultData.setData(roleGroup);
            resultData.setMsg("수정되었습니다.");
            resultData.setResultType(ResultType.SUCCESS);
        }else{
            resultData.setMsg("값이 존재하지않습니다.");
            resultData.setResultType(ResultType.FAIL);
        }
        return resultData;
    }

    @DeleteMapping(value="/api/roleGroups/{id}")
    public ResultData<Void> deleteRoleGroup(@PathVariable Long id){

        roleGroupService.deleteRoleGroup(id);
        
        ResultData<Void> resultData = new ResultData<>();
        resultData.setMsg("삭제되었습니다.");
        resultData.setResultType(ResultType.SUCCESS);
        return resultData;
    }

    @PostMapping(value="/api/roleGroups/{id}/roleList")
    public ResultData<RoleGroupDTO.WithoutPrivilegeDTO> modifyRoleGroupRoleList(@RequestBody RoleGroupDTO.WithoutPrivilegeDTO roleGroupDTO){

        ResultData<RoleGroupDTO.WithoutPrivilegeDTO> resultData = new ResultData<>();
        RoleGroupDTO.WithoutPrivilegeDTO roleGroup = roleGroupService.modifyRoleGroupRoleList(roleGroupDTO);
        if(roleGroup != null){
            resultData.setData(roleGroup);
            resultData.setMsg("수정되었습니다.");
            resultData.setResultType(ResultType.SUCCESS);
        }else{
            resultData.setMsg("값이 존재하지않습니다.");
            resultData.setResultType(ResultType.FAIL);
        }
        return resultData;

    }
}
