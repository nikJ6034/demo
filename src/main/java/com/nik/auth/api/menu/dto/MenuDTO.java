package com.nik.auth.api.menu.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.nik.auth.api.menu.code.MenuSe;
import com.nik.auth.api.menu.code.MenuType;
import com.nik.auth.api.role.dto.RoleDTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MenuDTO {
    private Long id;

    @NotNull(message = "메뉴 타입은 필수값입니다.")
    private MenuType menuType;

    @NotNull(message = "메뉴 구분은 필수값입니다.")
    private MenuSe menuSe;

    private int menuOrder;

    @NotBlank(message = "메뉴 이름은 필수값입니다.")
    private String menuNm;

    private UpperMenuDTO upperMenu;

    private List<MenuDTO> lowerMenu;

    private String menuPath;

    @NotBlank(message = "메뉴 설명은 필수값입니다.")
    private String menuDesc;

    private boolean useYn;

    private boolean visibleYn;

    private boolean delYn;

    private Set<RoleDTO> roleList = new HashSet<>();

    private Long regId;

    private LocalDateTime regDt;

    private Long mdfcnId;

    private LocalDateTime mdfcnDt;

}
