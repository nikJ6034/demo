package com.nik.auth.security.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class CustomUserDetailsServiceTest {

    @Autowired
    BCryptPasswordEncoder encodePassword;

    @Test
    void test() {
        System.out.println(encodePassword.encode("test1234"));
    }

}
