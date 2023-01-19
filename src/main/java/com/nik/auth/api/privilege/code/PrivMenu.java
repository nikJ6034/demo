package com.nik.auth.api.privilege.code;

import lombok.Getter;

@Getter
public enum PrivMenu implements PrivilegeType{
    
    READ("메뉴 조회", PrivMenu.P_READ)
    , WRITE("메뉴 등록", PrivMenu.P_WRITE)
    , MODIFY("메뉴 수정", PrivMenu.P_MODIFY)
    , DELETE("메뉴 삭제", PrivMenu.P_DELETE);
    
    public static final String PRIGPNAME = "메뉴";
    public static final String PRIGPVALUE = "MENU";
    
    public static final String P_READ = "READ_"+PRIGPVALUE;
    public static final String P_WRITE = "WRITE_"+PRIGPVALUE;
    public static final String P_MODIFY = "MODIFY_"+PRIGPVALUE;
    public static final String P_DELETE = "DELETE_"+PRIGPVALUE;

    private String priName;
    private String priValue;

    private PrivMenu(String priName, String priValue){
        this.priName = priName;
        this.priValue = priValue;
    }

}
