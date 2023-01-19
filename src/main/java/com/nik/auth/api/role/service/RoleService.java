package com.nik.auth.api.role.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.nik.auth.api.role.dto.RoleDTO;
import com.nik.auth.api.role.entity.Role;
import com.nik.auth.api.role.mapper.RoleMapper;
import com.nik.auth.api.role.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public List<RoleDTO> selectRoleList(){

        return roleRepository.selectRoleList();
    }

    public RoleDTO selectRole(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if(role.isPresent()){
            return roleMapper.toDto(role.get());
        }else{
            return null;
        }
    }

    public RoleDTO insertRole(RoleDTO roleDTO) {
        Role role = roleMapper.toEntity(roleDTO);
        Role save = roleRepository.save(role);
        return roleMapper.toDto(save);
    }

    public RoleDTO modifyRole(RoleDTO roleDTO) {
        Role role = roleMapper.toEntity(roleDTO);
        Optional<Role> findRoleOptional = roleRepository.findById(role.getId());

        if(findRoleOptional.isPresent()) {
            Role findRole = findRoleOptional.get();

            findRole.setRoleName(role.getRoleName());

            return roleMapper.toDto(findRole);
        }else {
            return null;
        }

    }

    public void deleteRole(Long id) {

        roleRepository.deleteById(id);

    }

    /**
     * 권한이 중복인지 확인
     * @param roleValue
     * @return
     */
    public boolean existsByRoleValue(String roleValue) {
        return roleRepository.existsByRoleValue(roleValue);
    }

    public RoleDTO modifyRolePrivileges(Long id, @Valid Set<String> privilegeList) {

        Optional<Role> findRole = roleRepository.findById(id);
        if(findRole.isPresent()){
            Role role = findRole.get();
            role.setPrivilegeList(privilegeList);

            return roleMapper.toDto(role);
        }else{
            return null;
        }
    }

    

}
