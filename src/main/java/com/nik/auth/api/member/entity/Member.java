package com.nik.auth.api.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.nik.auth.api.rolegroup.entity.RoleGroup;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name="TB_MEMBER")
@Entity
@Getter @Setter @ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String memberId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String memberPassword;

    @Column
    private String email;

    @Column
    private String mobile;

    @Column(nullable = false)
    private boolean delYn;

    @ManyToOne
    private RoleGroup roleGroup;
}
