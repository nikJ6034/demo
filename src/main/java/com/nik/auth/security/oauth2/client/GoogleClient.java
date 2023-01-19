package com.nik.auth.security.oauth2.client;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.nik.auth.security.oauth2.vo.Oauth2LoginVO;

public class GoogleClient implements Oauth2Client{

    public static final String CLIENT_NAME = "Google";

    private static  final String ATTR_NAME = "sub";

    private OAuth2User oAuth2User;
    public GoogleClient(OAuth2User oAuth2User) {
        this.oAuth2User = oAuth2User;
    }

    public Oauth2LoginVO getUser() {

        String id = oAuth2User.getAttribute(ATTR_NAME);

        return new Oauth2LoginVO(id, CLIENT_NAME, ATTR_NAME);

    }
}
