package com.nik.auth.security.dto;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.nik.auth.api.member.dto.MemberDTO;
import com.nik.auth.api.role.dto.RoleDTO;
import com.nik.auth.api.rolegroup.dto.RoleGroupDTO;

public class PrincipalDetails implements UserDetails, OAuth2User{
    private static final long serialVersionUID = 1L;
    private final Long id;
    private final String username;
    private final String password;
    private final Collection<SimpleGrantedAuthority> authorities;
    private final Collection<SimpleGrantedAuthority> roles;
    private final Collection<SimpleGrantedAuthority> privileges;

    public PrincipalDetails(Long id, String username, Set<SimpleGrantedAuthority> roles , Set<SimpleGrantedAuthority> privileges){
        this.id = id;
        this.username = username;
        this.password = null;
        this.roles = roles;
        this.privileges = privileges;

        Set<SimpleGrantedAuthority> authoritiesSet = new HashSet<>();
        authoritiesSet.addAll(roles);
        authoritiesSet.addAll(privileges);

        this.authorities = authoritiesSet;
    }

    public PrincipalDetails(MemberDTO memberDTO){
        this.id = memberDTO.getId();
        this.username = (StringUtils.isNotBlank(memberDTO.getMemberId()))?memberDTO.getMemberId():"id";
        this.password = memberDTO.getMemberPassword();
        
        Set<SimpleGrantedAuthority> roleSet = new HashSet<>();
        Set<SimpleGrantedAuthority> privilegeSet = new HashSet<>();

        RoleGroupDTO roleGroup = memberDTO.getRoleGroup();
        for(RoleDTO role : roleGroup.getRoleList()){
            roleSet.add(new SimpleGrantedAuthority(role.getRoleValue()));
            Set<String> privilegeList = role.getPrivilegeList();
            if(privilegeList != null && !privilegeList.isEmpty()){
                privilegeSet.addAll(role.getPrivilegeList()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet()));
            }
        }

        this.roles = roleSet;
        this.privileges = privilegeSet;
        Set<SimpleGrantedAuthority> authoritiesSet = new HashSet<>();
        authoritiesSet.addAll(roleSet);
        authoritiesSet.addAll(privilegeSet);
        authorities = authoritiesSet;
    }

    /**
     * @return the roles
     */
    public Collection<SimpleGrantedAuthority> getRoles() {
        return roles;
    }

    /**
     * @return the privilege
     */
    public Collection<SimpleGrantedAuthority> getPrivileges() {
        return privileges;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Collections.emptyMap();
    }

    @Override
    public String getName() {
        return this.username;
    }

    
    
}
