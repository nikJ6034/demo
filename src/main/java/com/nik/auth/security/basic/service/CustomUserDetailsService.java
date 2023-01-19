package com.nik.auth.security.basic.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nik.auth.api.member.dto.MemberDTO;
import com.nik.auth.api.member.service.MemberService;
import com.nik.auth.security.dto.PrincipalDetails;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService{

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MemberDTO selectMember = memberService.selectMemberByMemberId(username);

        if(selectMember == null) {
            throw new UsernameNotFoundException("회원정보가 일치하지 않습니다.");
        }

        return new PrincipalDetails(selectMember);
    }

}
