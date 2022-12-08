package com.la.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author LA
 * @createDate 2022-12-04-11:28
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced //这里必须要开负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
