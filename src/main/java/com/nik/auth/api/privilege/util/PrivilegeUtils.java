package com.nik.auth.api.privilege.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nik.auth.api.privilege.code.PrivilegeGroup;
import com.nik.auth.api.privilege.code.PrivilegeType;

public class PrivilegeUtils {

    private PrivilegeUtils() {
        throw new IllegalStateException("Utility class");
    }
    
    public static List<Map<String, Object>> getPrivilegeGroupList(){
        List<Map<String, Object>> privilegeGroupList = new ArrayList<>();

        for(PrivilegeGroup pg : PrivilegeGroup.values()){
            privilegeGroupList.add(getMap(pg.getPriGpValue(), pg.getPriGpName() ,pg.getPriValues()));
        }
        return privilegeGroupList;
    }

    public static Map<String, String> getPrivilegeMap(){
        Map<String, String> map =  new HashMap<>();
        for(PrivilegeGroup pg : PrivilegeGroup.values()){
            map.putAll(getMap(pg.getPriValues()));
        }
        return map;
    }

    private static Map<String, Object> getMap(String priGpValue, String priGpName, PrivilegeType[] values){
        Map<String, Object> map =  new HashMap<>();
        map.put("priGpValue", priGpValue);
        map.put("priGpName", priGpName);
        map.put("privilegeList", getPrivilegeList(values));
        return map;
    }

    private static Map<String, String> getMap(PrivilegeType[] values){
        Map<String, String> map =  new HashMap<>();
        for(PrivilegeType privilegeType : values){
            map.put(privilegeType.getPriValue(), privilegeType.getPriName());
        }
        return map;
    }

    private static List<Map<String, String>> getPrivilegeList(PrivilegeType[] values){
        List<Map<String, String>> privilegeList = new ArrayList<>();
        for(PrivilegeType pt : values){
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("priValue", pt.getPriValue());
            hashMap.put("priName", pt.getPriName());
            privilegeList.add(hashMap);
        }
        return privilegeList;
    }
    

}
