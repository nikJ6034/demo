package com.nik.auth.api.rolegroup.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.nik.auth.api.role.entity.Role;

import lombok.Data;

@Entity
@Table(name = "TB_ROLE_GROUP")
@Data
public class RoleGroup {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    //@Comment("Role 아이디")
    private Long id;

    @Column(name="GROUP_NAME",nullable = false)
    private String groupName;

    @Lob
    @Column(name="GROUP_DESC")
    private String groupDesc;

    @ManyToMany
    @JoinTable(name = "TB_ROLE_GP_ROLE", joinColumns = @JoinColumn(name = "ROLE_GROUP_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private List<Role> roleList;

}
