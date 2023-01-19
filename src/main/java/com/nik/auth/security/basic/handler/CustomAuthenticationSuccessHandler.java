package com.nik.auth.security.basic.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.nik.auth.security.dto.PrincipalDetails;
import com.nik.auth.security.jwt.service.TokenService;
import com.nik.auth.security.jwt.vo.Token;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final TokenService tokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

        PrincipalDetails customUserDetails = (PrincipalDetails)authentication.getPrincipal();

        Token token = tokenService.generateToken(customUserDetails);
        request.setAttribute("token", token);

        request.getRequestDispatcher("/token/access").forward(request, response);

    }

}
