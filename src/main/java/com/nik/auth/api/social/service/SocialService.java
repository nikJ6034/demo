package com.nik.auth.api.social.service;

import org.springframework.stereotype.Service;

import com.nik.auth.api.social.dto.SocialDTO;
import com.nik.auth.api.social.entity.Social;
import com.nik.auth.api.social.mapper.SocialMapper;
import com.nik.auth.api.social.repository.SocialRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SocialService {
    
    public final SocialRepository socialRepository;
    public final SocialMapper socialMapper;

    public SocialDTO findByClientId(String clientId){
        Social social = socialRepository.findByClientId(clientId);
        return socialMapper.toDto(social);
    }
}
