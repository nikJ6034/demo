package com.nik.auth.security.oauth2.client;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.nik.auth.security.oauth2.vo.Oauth2LoginVO;

public class KakaoClient implements Oauth2Client{

    public static final String CLIENT_NAME = "Kakao";
    private static  final String ATTR_NAME = "id";

    private OAuth2User oAuth2User;

    public KakaoClient(OAuth2User oAuth2User) {
        this.oAuth2User = oAuth2User;
    }

    @Override
    public Oauth2LoginVO getUser() {
        Object attribute = oAuth2User.getAttribute(ATTR_NAME);
        String id = null;
        if(attribute != null){
            id = attribute.toString();
        }
        return new Oauth2LoginVO(id , CLIENT_NAME, ATTR_NAME);
    }

}
