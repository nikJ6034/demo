package com.nik.auth.api.commoncode.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nik.auth.api.member.entity.Member;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_UPPER_CODE")
public class UpperCode {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 50, nullable = false, name = "ID")
    private Long id;

    //코드
    @Column(length = 50, name = "CODE", nullable = false, unique = true, updatable = false)
    private String code;
    
    //코드명
    @Column(length = 100, name = "NAME", nullable = false)
    private String name;

    //코드 설명
    @Column(length = 500, name = "DESC")
    private String desc;

    @OneToMany(mappedBy = "upperCode", cascade = CascadeType.ALL)
    private List<CommonCode> codeList;

    //등록자아이디
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "REG_ID" , updatable = false)
    private Member regId;

    //등록일
    @Column(nullable = false, name = "REG_DT", updatable = false)
    private LocalDateTime regDt;

    //수정자아이디
    @ManyToOne(optional = true)
    @JoinColumn(nullable = true, name = "MDFCN_ID")
    private Member mdfcnId;

    //수정일
    @Column(nullable = true, name = "MDFCN_DT")
    private LocalDateTime mdfcnDt;
}
