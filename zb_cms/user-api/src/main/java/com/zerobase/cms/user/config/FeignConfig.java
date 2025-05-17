package com.zerobase.cms.user.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Value("{mailgun.key}")
    public static final String KEY = "";

    @Qualifier(value = "mailgun")
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("api", KEY);
    }
}
