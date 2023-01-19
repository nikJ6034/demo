package com.nik.auth.api.bbs.notice.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nik.auth.api.bbs.entity.Bbs;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "TB_NOTICE")
@Getter @Setter @EqualsAndHashCode(callSuper = false)
public class Notice extends Bbs{

    //공지사항 아이디
    @Id
    @Column(name="ID" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //공지 시작일
    @Column(name="BEGIN_DT", nullable = false)
    private LocalDateTime beginDt;

    //공지 종료일
    @Column(name="END_DT", nullable = false)
    private LocalDateTime endDt;

    //첨부파일 그룹 아이디
    @Column(name="ATTACH_FILE_GROUP_ID", nullable = true)
    private Long attachFileGroupId;

    //사용여부
    @Column(name="USE_YN" )
    private boolean useYn;
}
