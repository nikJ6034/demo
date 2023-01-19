package com.nik.auth.api.bbs.faq.entity;

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
@Table(name = "TB_FAQ")
@Getter @Setter @EqualsAndHashCode(callSuper = false)
public class Faq extends Bbs {
    
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //FAQ 구분
    @Column(name="SE", nullable = false)
    private String se;

    //FAQ 정렬순번
    @Column(name="SORT", nullable = false)
    private int sort;
    //FAQ 사용유무
    @Column(name="USE_YN", nullable = false)
    private boolean useYn;
}
