package com.nik.auth.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.nik.auth.security.dto.PrincipalDetails;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthorizationChecker {

    //private final PrivilegeService privilegeService;

    public boolean check(Authentication authentication, String... role) {
        if(!"anonymousUser".equals(authentication.getName())) {
            PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
            return true;//privilegeService.isPrivilege(principalDetails.getAuthorities(), role);

        }else {
            return false;
        }

    }
}
