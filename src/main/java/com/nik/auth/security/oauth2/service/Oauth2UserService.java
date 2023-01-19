package com.nik.auth.security.oauth2.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.nik.auth.api.member.dto.MemberDTO;
import com.nik.auth.api.social.dto.SocialDTO;
import com.nik.auth.api.social.service.SocialService;
import com.nik.auth.security.dto.PrincipalDetails;
import com.nik.auth.security.oauth2.client.Oauth2Client;
import com.nik.auth.security.oauth2.client.Oauth2ClientFactory;
import com.nik.auth.security.oauth2.vo.Oauth2LoginVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class Oauth2UserService extends DefaultOAuth2UserService{

    private final SocialService socialService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        
        if(oAuth2User == null) {
            throw new OAuth2AuthenticationException("회원정보가 없습니다.");
        }else {

            String clientName = userRequest.getClientRegistration().getClientName();

            Oauth2Client create = Oauth2ClientFactory.create(clientName, oAuth2User);

            Oauth2LoginVO user = create.getUser();

            SocialDTO socialDTO = socialService.findByClientId(user.getClientId());

            MemberDTO memberDTO = socialDTO.getMember();

            if(memberDTO == null){
                //소셜 테이블 등록 후
                //회원가입 페이지로! (미구현)
                return null;
            }else{
                return new PrincipalDetails(memberDTO);
            }
        }

    }
}
