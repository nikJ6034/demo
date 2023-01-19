package com.nik.auth.security.oauth2.vo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Oauth2LoginVO {
    private final String clientId;
    private final String clientName;
    private final String name;
}
