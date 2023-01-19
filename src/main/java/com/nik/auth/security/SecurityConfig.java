package com.nik.auth.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nik.auth.security.basic.handler.CustomAuthenticationSuccessHandler;
import com.nik.auth.security.basic.service.CustomUserDetailsService;
import com.nik.auth.security.handler.CustomAuthenticationFailureHandler;
import com.nik.auth.security.jwt.JwtAccessDeniedHandler;
import com.nik.auth.security.jwt.JwtAuthenticationEntryPoint;
import com.nik.auth.security.jwt.filter.JwtAuthFilter;
import com.nik.auth.security.jwt.repository.HttpCookieOAuth2AuthorizationRepository;
import com.nik.auth.security.jwt.service.TokenService;
import com.nik.auth.security.oauth2.handler.CustomOAuth2AuthenticationSuccessHandler;
import com.nik.auth.security.oauth2.service.Oauth2UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final Oauth2UserService oauth2UserService;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomOAuth2AuthenticationSuccessHandler customOAuth2AuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final TokenService tokenService;

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final HttpCookieOAuth2AuthorizationRepository httpCookieOAuth2AuthorizationRepository;

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/h2-console/**","/favicon.ico");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
        //.authenticationProvider(customAuthenticationProvider)
        .userDetailsService(customUserDetailsService)
        .passwordEncoder(encodePassword());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //공통 설정.
        http
            .cors().and()
            .csrf().disable()
            .headers()
            .frameOptions()
            .disable()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler)
            .and()

            .authorizeRequests()
            .antMatchers("/", "/token/**", "/api/**", "/oauth/**", "/oauth2/**", "/login/oauth2/**","/user/**", "/auth/error").permitAll()
            //.antMatchers("/api/**").access("@authorizationChecker.check(request, authentication)")
            //.antMatchers().hasRole("USER")
            .anyRequest().authenticated()
            //세션을 사용하지 않는다. Jwt토큰 인증 방식 사용.
        ;

        //기존 로그인 방식 설정.
        http
            .formLogin()
            .loginProcessingUrl("/api/login/basic")
            .usernameParameter("memberId")
            .passwordParameter("password")
            .successHandler(customAuthenticationSuccessHandler)
            .failureHandler(customAuthenticationFailureHandler)
        ;
        // Oauth2 로그인 설정.
        http
            .oauth2Login()
            .authorizationEndpoint()
            .authorizationRequestRepository(httpCookieOAuth2AuthorizationRepository)
            .and()

            .userInfoEndpoint()
            .userService(oauth2UserService)
            .and()

            .successHandler(customOAuth2AuthenticationSuccessHandler)
            .failureHandler(customAuthenticationFailureHandler)
//            .defaultSuccessUrl("/user/member")
//            .redirectionEndpoint()
//            .baseUri("/login/oauth2/code/google")
//            .loginPage("/oauth2/login")
//            .authorizedClientService(authorizedClientService())
        ;

        //jwt 인증 방식 설정.
        http.addFilterBefore(new JwtAuthFilter(tokenService), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
        configuration.addAllowedHeader("*");
//        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
