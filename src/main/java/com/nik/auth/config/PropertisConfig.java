package com.nik.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:properties/application-common.properties")
@PropertySource("classpath:properties/application-${spring.profiles.active}.properties")
public class PropertisConfig {

}
