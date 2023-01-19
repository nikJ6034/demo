package com.nik.auth.api.rolegroup.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.nik.auth.api.role.mapper.RoleMapper;
import com.nik.auth.api.rolegroup.dto.RoleGroupDTO;
import com.nik.auth.api.rolegroup.entity.RoleGroup;
import com.nik.auth.api.rolegroup.mapper.RoleGroupMapper;
import com.nik.auth.api.rolegroup.repository.RoleGroupRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleGroupService {
    
    private final RoleGroupRepository roleGroupRepository;
    private final RoleGroupMapper roleGroupMapper;
    private final RoleMapper roleMapper;

    /**
     * RoleGroup목록을 조회합니다.
     * @return
     */
    public List<RoleGroupDTO.WithoutPrivilegeDTO> getRoleGroups(){
        List<RoleGroup> getRoleGroups = roleGroupRepository.findAll();
        return roleGroupMapper.toWithoutPrivilegeDto(getRoleGroups);
    }

    /**
     * RoleGroup을 등록합니다.
     * @param roleGroupDTO
     * @return
     */
    public RoleGroupDTO.WithoutPrivilegeDTO insertRoleGroup(RoleGroupDTO.WithoutPrivilegeDTO roleGroupDTO){
        RoleGroup roleGroup = roleGroupMapper.toWithoutPrivilegeEntity(roleGroupDTO);
        return roleGroupMapper.toWithoutPrivilegeDto(roleGroupRepository.save(roleGroup));
    }

    /**
     * RoleGroup을 등록합니다.
     * @param roleGroupDTO
     * @return
     */
    public RoleGroupDTO.WithoutPrivilegeDTO modifyRoleGroup(RoleGroupDTO.WithoutPrivilegeDTO roleGroupDTO){
        Optional<RoleGroup> findById = roleGroupRepository.findById(roleGroupDTO.getId());

        if(findById.isPresent()){
            RoleGroup roleGroup = findById.get();
            roleGroup.setGroupDesc(roleGroupDTO.getGroupDesc());
            roleGroup.setGroupName(roleGroupDTO.getGroupName());
            return roleGroupMapper.toWithoutPrivilegeDto(roleGroup);
        }else{
            return null;
        }
    }

    public void deleteRoleGroup(Long id){
        roleGroupRepository.deleteById(id);
    }

    public RoleGroupDTO.WithoutPrivilegeDTO modifyRoleGroupRoleList(RoleGroupDTO.WithoutPrivilegeDTO roleGroupDTO) {
        Optional<RoleGroup> findById = roleGroupRepository.findById(roleGroupDTO.getId());

        if(findById.isPresent()){
            RoleGroup roleGroup = findById.get();
            roleGroup.setRoleList(roleMapper.toWithoutPrivilegeEntity(roleGroupDTO.getRoleList()));
            return roleGroupMapper.toWithoutPrivilegeDto(roleGroup);
        }else{
            return null;
        }
    }
}
