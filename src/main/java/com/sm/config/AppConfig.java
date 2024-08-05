package com.sm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class AppConfig {

    @Value("${cloudinary.cloud.name}")
    private String cloudName;
    @Value("${cloudinary.api.key}")
    private String apiKey;
    @Value("${cloudinary.api.secret}")
    private String apiSecret;
    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(
            
            ObjectUtils.asMap(
                "cloud name",cloudName,
                "api-key",apiKey,
                "api-secret",apiSecret
            )

        );
    }
}