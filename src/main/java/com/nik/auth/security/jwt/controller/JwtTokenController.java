package com.nik.auth.security.jwt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nik.auth.api.member.dto.MemberDTO;
import com.nik.auth.api.member.service.MemberService;
import com.nik.auth.error.exceptions.UnauthorizedException;
import com.nik.auth.security.dto.PrincipalDetails;
import com.nik.auth.security.jwt.service.TokenService;
import com.nik.auth.security.jwt.vo.Token;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class JwtTokenController {

    private final TokenService tokenService;
    private final MemberService memberService;

    @RequestMapping(value="/token/access" , produces = MediaType.APPLICATION_JSON_VALUE )
    public Token accessAuth(HttpServletRequest request, HttpServletResponse response) {

        return (Token)request.getAttribute("token");

    }

    @GetMapping(value="/token/refresh" , produces = MediaType.APPLICATION_JSON_VALUE )
    public Token refreshAuth(HttpServletRequest request, HttpServletResponse response) throws UnauthorizedException {
        String refreshToken = request.getHeader("refreshToken");

        if (refreshToken != null && tokenService.verifyToken(refreshToken)) {

            MemberDTO memberDTO = memberService.selectMember(tokenService.getUserId(refreshToken));

            if(memberDTO == null) {
                throw new UnauthorizedException("유저 정보가 존재하지 않습니다.");
            }else {
                return tokenService.generateToken(new PrincipalDetails(memberDTO));
            }

        }else {
            throw new UnauthorizedException("토큰만료일이 지났거나 유료하지 않는 토큰입니다.");
        }

    }

}
