package com.nik.auth.api.role.entity;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TB_ROLE")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    //@Comment("Role 아이디")
    private Long id;

    @Column(name="ROLE_VALUE", nullable = false, unique = true)
    //@Comment("Role 값")
    private String roleValue;

    //@Comment("Role 이름")
    @Column(name="ROLE_NAME",nullable = false)
    private String roleName;

    @ElementCollection
    @CollectionTable(name = "TB_ROLE_PRIVILEGE")
    @Column(name="PRIVILEGE")
    private Set<String> privilegeList;

}
