package com.fjgcxy.xyb.auto.clock.in.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OkHttpConfig {


    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder().build();
    }

}
