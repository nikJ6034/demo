package com.nik.auth.api.privilege.code;

import lombok.Getter;

@Getter
public enum PrivRole implements PrivilegeType{
    READ("역할 조회", PrivRole.P_READ)
    , WRITE("역할 등록", PrivRole.P_WRITE)
    , MODIFY("역할 수정", PrivRole.P_MODIFY)
    , DELETE("역할 삭제", PrivRole.P_DELETE);

    public static final String PRIGPNAME = "역할";
    public static final String PRIGPVALUE = "ROLE";
    
    public static final String P_READ = "READ_"+PRIGPVALUE;
    public static final String P_WRITE = "WRITE_"+PRIGPVALUE;
    public static final String P_MODIFY = "MODIFY_"+PRIGPVALUE;
    public static final String P_DELETE = "DELETE_"+PRIGPVALUE;

    private String priName;
    private String priValue;

    private PrivRole(String priName, String priValue){
        this.priName = priName;
        this.priValue = priValue;
    }

}
