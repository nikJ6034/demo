package com.nik.auth.api.attachfile.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_ATTACH_FILE")
public class AttachFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
//    @Comment("첨부파일 아이디")
    @Column(name="ID")
    private Long id;

    @ManyToOne(optional = true)
    private AttachFileGroup attachFileGroup;

//    @Comment("원본파일명")
    @Column(name="ORGIN_NAME", length = 300, nullable = false)
    private String orginName;

//    @Comment("임시파일명")
    @Column(name="TEMP_NAME", length = 300, nullable = false)
    private String tempName;

//    @Comment("전체경로")
    @Column(name="FULL_PATH", length = 500, nullable = false)
    private String fullPath;

//    @Comment("용량")
    @Column(name="SIZE", nullable = false)
    private long size;

//    @Comment("확장자명")
    @Column(name="EXT", length = 50, nullable = false)
    private String ext;

//    @Comment("순번")
    @Column(name="FILE_ORDER", length = 3)
    private int order;

//    @Comment("등록일")
    @Column(name="REG_DT", nullable = false)
    private LocalDateTime regDt;

    @Column(name="UUID", nullable = false, updatable = false, unique = true, length = 36)
    private String uuid;


}
