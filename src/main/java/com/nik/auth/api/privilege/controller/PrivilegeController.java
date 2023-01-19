package com.nik.auth.api.privilege.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nik.auth.api.privilege.code.PrivRole;
import com.nik.auth.api.privilege.util.PrivilegeUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PrivilegeController {

    @PreAuthorize("hasAuthority('" + PrivRole.P_READ + "')")
    @GetMapping(value = "/api/group/privileges")
    public List<Map<String, Object>> selectPrivilegeGroupList() {
        return PrivilegeUtils.getPrivilegeGroupList();
    }

    @PreAuthorize("hasAuthority('" + PrivRole.P_READ + "')")
    @GetMapping(value = "/api/group/privilegeMap")
    public Map<String, String> selectPrivilegeMap() {
        return PrivilegeUtils.getPrivilegeMap();
    }

    // //@PreAuthorize("@authorizationChecker.check(authentication,'ROLE_WRITE')")
    // @PostMapping(value="/api/group/privileges", consumes =
    // MediaType.APPLICATION_ATOM_XML_VALUE)
    // public ResultData<PrivilegeGroup> insertPrivilegeGroup(@RequestBody @Valid
    // PrivilegeGroupVO privilegeGroupVO) {

    // ResultData<PrivilegeGroup> resultData = new ResultData<>();

    // PrivilegeGroup privilegeGroup = privilegeGroupVO.toEntity();

    // resultData.setMsg("등록되었습니다.");
    // resultData.setResultType(ResultType.SUCCESS);
    // resultData.setData(privilegeService.insertPrivilegeGroup(privilegeGroup));

    // return resultData;
    // }

    // //@PreAuthorize("@authorizationChecker.check(authentication,'ROLE_MODIFY')")
    // @PutMapping(value="/api/group/privileges")
    // public ResultData<PrivilegeGroup> modifyPrivilegeGroup(@RequestBody @Valid
    // PrivilegeGroupVO privilegeGroupVO) {

    // ResultData<PrivilegeGroup> resultData = new ResultData<>();

    // PrivilegeGroup privilegeGroup = privilegeGroupVO.toEntity();

    // PrivilegeGroup modifyPrivilegeGroup =
    // privilegeService.modifyPrivilegeGroup(privilegeGroup);

    // if(modifyPrivilegeGroup == null) {
    // resultData.setMsg("데이터가 존재하지 않습니다.");
    // resultData.setResultType(ResultType.FAIL);
    // }else {
    // resultData.setMsg("수정되었습니다.");
    // resultData.setResultType(ResultType.SUCCESS);
    // resultData.setData(modifyPrivilegeGroup);
    // }

    // return resultData;
    // }

    // //@PreAuthorize("@authorizationChecker.check(authentication,'ROLE_DELETE')")
    // @DeleteMapping(value="/api/group/privileges/{privilegeGroupId}")
    // public ResultData<Void> deletePrivilegeGroup(@PathVariable Long
    // privilegeGroupId) {

    // ResultData<Void> resultData = new ResultData<>();

    // boolean existPrivilege =
    // privilegeService.existsPrivilegeRoleByPrivilegeGroupId(privilegeGroupId);

    // if(!existPrivilege) {
    // privilegeService.deletePrivilegeGroup(privilegeGroupId);
    // resultData.setMsg("삭제되었습니다.");
    // resultData.setResultType(ResultType.SUCCESS);
    // }else {
    // resultData.setMsg("설정된 업무권한이 존재합니다. 업무권한을 해제해주세요.");
    // resultData.setResultType(ResultType.FAIL);
    // }

    // return resultData;
    // }

    // //@PreAuthorize("@authorizationChecker.check(authentication,'ROLE_READ')")
    // @GetMapping(value="/api/group/privileges/{privilegeGpId}")
    // public PrivilegeGroupVO selectPrivilegeList(@PathVariable Long privilegeGpId)
    // {
    // return privilegeService.selectPrivilegeGroup(privilegeGpId);
    // }

    // //@PreAuthorize("@authorizationChecker.check(authentication,'ROLE_READ')")
    // @GetMapping(value="/api/role/privileges/{roleId}")
    // public List<PrivilegeVO> selectPrivilegeListByRoleId(@PathVariable Long
    // roleId) {
    // return privilegeService.selectPrivilegeListByRoleId(roleId);
    // }

    // //@PreAuthorize("@authorizationChecker.check(authentication,'ROLE_WRITE')")
    // @PostMapping(value="/api/role/privileges")
    // public ResultData<Void> updatePrivilegeRole(@RequestBody RolePrivilegeVO
    // rolePrivilegeVO){
    // ResultData<Void> resultData = new ResultData<>();

    // privilegeService.updatePrivilegeRole(rolePrivilegeVO);
    // resultData.setMsg("수정되었습니다.");
    // resultData.setResultType(ResultType.SUCCESS);

    // return resultData;
    // }

    // //@PreAuthorize("@authorizationChecker.check(authentication,'ROLE_WRITE')")
    // @PostMapping(value="/api/privileges")
    // public ResultData<Privilege> insertPrivilege(@RequestBody @Valid PrivilegeVO
    // privilegeVO) {

    // ResultData<Privilege> resultData = new ResultData<>();

    // boolean existsByPriValue =
    // privilegeService.existsByPriValue(privilegeVO.getPriValue());

    // if(existsByPriValue) {
    // resultData.setMsg("이미 등록된 업무권한입니다.");
    // resultData.setResultType(ResultType.FAIL);
    // }else {
    // resultData.setMsg("등록되었습니다.");
    // resultData.setResultType(ResultType.SUCCESS);
    // Privilege privilege = privilegeVO.toEntity();
    // resultData.setData(privilegeService.insertPrivilege(privilege));

    // }

    // return resultData;
    // }

    // //@PreAuthorize("@authorizationChecker.check(authentication,'ROLE_MODIFY')")
    // @PutMapping(value="/api/privileges")
    // public ResultData<Privilege> modifyPrivilege(@RequestBody @Valid PrivilegeVO
    // privilegeVO) {

    // ResultData<Privilege> resultData = new ResultData<>();

    // Privilege privilege = privilegeVO.toEntity();
    // Privilege modifyPrivilege = privilegeService.modifyPrivilege(privilege);

    // if(modifyPrivilege != null) {
    // resultData.setMsg("수정되었습니다.");
    // resultData.setResultType(ResultType.SUCCESS);
    // resultData.setData(modifyPrivilege);
    // }else {
    // resultData.setMsg("데이터가 존재하지 않습니다.");
    // resultData.setResultType(ResultType.FAIL);
    // }

    // return resultData;
    // }

    // //@PreAuthorize("@authorizationChecker.check(authentication,'ROLE_DELETE')")
    // @DeleteMapping(value="/api/privileges/{privilegeId}")
    // public ResultData<Void> deletePrivilege(@PathVariable String privilegeId) {

    // ResultData<Void> resultData = new ResultData<>();

    // boolean existPrivilegeRole =
    // privilegeService.existsPrivilegeRoleByPrivilegeId(privilegeId);

    // if(existPrivilegeRole) {
    // resultData.setMsg("설정된 업무권한이 존재합니다. 업무권한을 해제해주세요.");
    // resultData.setResultType(ResultType.FAIL);
    // }else {
    // privilegeService.deletePrivilege(privilegeId);
    // resultData.setMsg("삭제되었습니다.");
    // resultData.setResultType(ResultType.SUCCESS);

    // }

    // return resultData;
    // }

}
