package com.nik.auth.security.jwt.service;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.nik.auth.security.dto.PrincipalDetails;
import com.nik.auth.security.jwt.vo.Token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    private String secretKey = "token-secret-key";

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public Token generateToken(PrincipalDetails customUserDetails) {
        long tokenPeriod = 1000L * 60L * 10L * 24L;
        long refreshPeriod = 1000L * 60L * 60L * 24L * 30L * 3L;

        Claims accesstokenClaims = Jwts.claims();
        accesstokenClaims.put("roles", customUserDetails.getRoles().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
        accesstokenClaims.put("privileges", customUserDetails.getPrivileges().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
        accesstokenClaims.put("id", customUserDetails.getId());
        accesstokenClaims.put("userName", customUserDetails.getName());

        Claims refreshtokenClaims = Jwts.claims();
        refreshtokenClaims.put("id", customUserDetails.getId());

        Date now = new Date();
        return new Token(
            Jwts.builder()
                .setClaims(accesstokenClaims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenPeriod))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact(),
            Jwts.builder()
                .setClaims(refreshtokenClaims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshPeriod))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact());
    }

    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

        return claims.getBody()
                    .getExpiration()
                    .after(new Date());
        }catch(Exception e) {
            return false;
        }
    }

    public PrincipalDetails getUserInfo(String accessToken) {

        Claims body = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(accessToken).getBody();
        long id = ((int)body.get("id"));
        String userName = (String)body.get("userName");
        @SuppressWarnings("unchecked")
        List<String> roles = (List<String>)body.get("roles");
        @SuppressWarnings("unchecked")
        List<String> privileges = (List<String>)body.get("privileges");

        return new PrincipalDetails(id
            , userName
            , roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet())
            , privileges.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet())
        );

    }

    public long getUserId(String refreshToken){
        Claims body = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(refreshToken).getBody();
        return ((int)body.get("id"));
    }

}
