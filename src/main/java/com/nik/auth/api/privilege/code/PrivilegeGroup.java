package com.nik.auth.api.privilege.code;

import lombok.Getter;

@Getter
public enum PrivilegeGroup{
    ROLE(PrivRole.PRIGPNAME, PrivRole.PRIGPVALUE, PrivRole.values()),
    MENU(PrivNotice.PRIGPNAME, PrivNotice.PRIGPVALUE, PrivNotice.values()),
    NOTICE(PrivMenu.PRIGPNAME, PrivMenu.PRIGPVALUE, PrivMenu.values());
    
    private final String priGpName;
    private final String priGpValue;
    private final PrivilegeType[] priValues;

    private PrivilegeGroup(String priGpName, String priGpValue, final PrivilegeType[] priValues){
        this.priGpName = priGpName;
        this.priGpValue = priGpValue;
        this.priValues = priValues;
    }

}
