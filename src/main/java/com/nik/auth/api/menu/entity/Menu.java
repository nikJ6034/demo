package com.nik.auth.api.menu.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.nik.auth.api.member.entity.Member;
import com.nik.auth.api.menu.code.MenuSe;
import com.nik.auth.api.menu.code.MenuType;
import com.nik.auth.api.role.entity.Role;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_MENU")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Comment("메뉴아이디")
    @Column(length = 50, nullable = false, name = "ID")
    private Long id;

    //@Comment("메뉴분류")
    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false, name = "MENU_TYPE")
    private MenuType menuType;

    //@Comment("메뉴구분")
    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false, name = "MENU_SE")
    private MenuSe menuSe;

    //@Comment("메뉴순서")
    @Column(length = 5, nullable = false, name = "MENU_ORDER")
    private int menuOrder;

    //@Comment("메뉴이름")
    @Column(length = 100, nullable = false, name = "MENU_NM")
    private String menuNm;

    //@Comment("상위메뉴아이디")
    @ManyToOne(optional = true)
    @JoinColumn(name = "UPPER_MENU_ID")
    private Menu upperMenu;

    //@Comment("하위메뉴")
    @OneToMany
    @JoinColumn(name = "UPPER_MENU_ID", referencedColumnName = "ID")
    private List<Menu> lowerMenu;

    //@Comment("경로")
    @Column(length = 500, name = "MENU_PATH")
    private String menuPath;


    //@Comment("메뉴설명")
    @Column(length = 4000, nullable = false, name = "MENU_DESC")
    private String menuDesc;

    //@Comment("사용유무")
    @Column(nullable = false, name = "USE_YN")
    private boolean useYn;

    @Column(nullable = false, name = "VISIBLE_YN")
    @ColumnDefault("false")
    private boolean visibleYn;

    //@Comment("삭제유무")
    @Column(nullable = false, name = "DEL_YN")
    private boolean delYn;

    @ManyToMany
    @JoinTable(name = "TB_MENU_ROLE", joinColumns = @JoinColumn(name = "MENU_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private List<Role> roleList;

    //@Comment("등록자아이디")
    @ManyToOne(optional = false)
    @JoinColumn(name = "REG_ID" , updatable = false)
    private Member regId;

    //@Comment("등록일")
    @Column(nullable = false, name = "REG_DT", updatable = false)
    private LocalDateTime regDt;

    //@Comment("수정자아이디")
    @ManyToOne(optional = true)
    @JoinColumn(name = "MDFCN_ID")
    private Member mdfcnId;

    //@Comment("수정일")
    @Column(nullable = true, name = "MDFCN_DT")
    private LocalDateTime mdfcnDt;

}
