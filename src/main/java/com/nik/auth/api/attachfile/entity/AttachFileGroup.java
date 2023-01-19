package com.nik.auth.api.attachfile.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_ATTACH_FILE_GROUP")
public class AttachFileGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Comment("첨부파일 그룹 아이디")
    @Column(name = "ID")
    private Long id;

    @OneToMany(mappedBy = "attachFileGroup", fetch = FetchType.EAGER)
    private List<AttachFile> attachFileList;
}
