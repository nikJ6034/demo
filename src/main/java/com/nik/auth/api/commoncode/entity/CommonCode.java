package com.nik.auth.api.commoncode.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.nik.auth.api.member.entity.Member;

import lombok.Data;

@Data
@Entity
@Table(
    name="TB_CODE",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"UPPER_CODE_ID", "CODE"})
    }
)
public class CommonCode {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //코드
    @Column(length = 50, name = "CODE", nullable = false, updatable = false)
    private String code;
    
    //코드명
    @Column(length = 100, name = "NAME", nullable = false)
    private String name;

    //코드 설명
    @Column(length = 500, name = "DESC")
    private String desc;

    @ManyToOne
    @JoinColumn(name = "UPPER_CODE_ID")
    private UpperCode upperCode;

    //등록자아이디
    @ManyToOne(optional = false)
    @JoinColumn(name = "REG_ID" , updatable = false)
    private Member regId;

    //등록일
    @Column(nullable = false, name = "REG_DT", updatable = false)
    private LocalDateTime regDt;

    //수정자아이디
    @ManyToOne(optional = true)
    @JoinColumn(name = "MDFCN_ID")
    private Member mdfcnId;

    //수정일
    @Column(nullable = true, name = "MDFCN_DT")
    private LocalDateTime mdfcnDt;
}
