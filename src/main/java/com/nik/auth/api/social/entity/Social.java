package com.nik.auth.api.social.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nik.auth.api.member.entity.Member;

import lombok.Data;

@Table(name = "TB_SOCIAL")
@Entity
@Data
public class Social {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = true)
    private String clientId;

    @Column(nullable = true)
    private String clientName;

    @Column(unique = true, nullable = false)
    private String uuid;

    @ManyToOne(optional = true)
    private Member member;
    
}
