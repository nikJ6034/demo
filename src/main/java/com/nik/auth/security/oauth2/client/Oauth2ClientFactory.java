package com.nik.auth.security.oauth2.client;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Oauth2ClientFactory {

    private Oauth2ClientFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static Oauth2Client create(String clientName, OAuth2User oAuth2User) throws OAuth2AuthenticationException {


        if (KakaoClient.CLIENT_NAME.equals(clientName)) {
            return new KakaoClient(oAuth2User);
        }else if(GoogleClient.CLIENT_NAME.equals(clientName)) {
            return new GoogleClient(oAuth2User);
        } else {
            throw new OAuth2AuthenticationException("존재하지 않는 클라이언트 입니다.");
        }

    }
}
