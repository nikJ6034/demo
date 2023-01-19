package com.nik.auth.api.menu.code;

import lombok.Getter;

@Getter
public enum MenuSe {
    DIRECTORY("디렉토리"),
    PAGE("페이지"),
    LINK("링크"),
    CONTENT("컨텐츠")
    ;

    private String name;

    MenuSe(String name) {
        this.name= name;
    }
}
