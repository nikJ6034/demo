package com.nik.auth.api.menu.code;

import lombok.Getter;

@Getter
public enum MenuType {
    ADMIN("관리자")
    , PORTAL("포탈")
    ;

    private String name;

    MenuType(String name) {
        this.name= name;
    }
}
