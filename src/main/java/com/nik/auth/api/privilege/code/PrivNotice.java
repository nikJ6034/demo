package com.nik.auth.api.privilege.code;

import lombok.Getter;

@Getter
public enum PrivNotice implements PrivilegeType{

    READ("공지사항 조회", PrivNotice.P_READ)
    , WRITE("공지사항 등록", PrivNotice.P_WRITE)
    , MODIFY("공지사항 수정", PrivNotice.P_MODIFY)
    , DELETE("공지사항 삭제", PrivNotice.P_DELETE);
    
    public static final String PRIGPNAME = "공지사항";
    public static final String PRIGPVALUE = "NOTICE";
    
    public static final String P_READ = "READ_"+PRIGPVALUE;
    public static final String P_WRITE = "WRITE_"+PRIGPVALUE;
    public static final String P_MODIFY = "MODIFY_"+PRIGPVALUE;
    public static final String P_DELETE = "DELETE_"+PRIGPVALUE;

    private String priName;
    private String priValue;

    private PrivNotice(String priName, String priValue){
        this.priName = priName;
        this.priValue = priValue;
    }

}
