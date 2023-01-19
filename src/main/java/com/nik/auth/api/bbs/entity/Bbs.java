package com.nik.auth.api.bbs.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.nik.auth.api.member.entity.Member;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter @Setter
public class Bbs {

    //제목
    @Column(nullable = false, name="TITLE" )
    private String title;

    //내용
    @Column(nullable = false, name="CONTENT")
    @Lob
    private String content;

    //등록자아이디
    @ManyToOne(optional = false)
    @JoinColumn(name = "REG_ID", updatable = false)
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


    //삭제유무
    @Column(nullable = false, name = "DEL_YN")
    private boolean delYn;

}
