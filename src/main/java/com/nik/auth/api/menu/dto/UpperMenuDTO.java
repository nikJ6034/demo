package com.nik.auth.api.menu.dto;

import com.nik.auth.api.menu.code.MenuSe;
import com.nik.auth.api.menu.code.MenuType;

import lombok.Data;

@Data
public class UpperMenuDTO {
    private Long id;

    private MenuType menuType;

    private MenuSe menuSe;

    private int menuOrder;

    private String menuNm;

    private String menuPath;
}
