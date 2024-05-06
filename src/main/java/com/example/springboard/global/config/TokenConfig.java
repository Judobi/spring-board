package com.example.springboard.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application-token.properties")
public class TokenConfig {
    @Value("${token.secret_key}")
    private String secretKey;


}
