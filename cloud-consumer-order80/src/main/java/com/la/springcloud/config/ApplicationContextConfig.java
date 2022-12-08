package com.la.springcloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author LA
 * @createDate 2022-11-30-23:19
 */
@Configuration
public class ApplicationContextConfig {

    //向容器中注入调用远程端口方法的对象，要先注入，才能使用
    @Bean
    //要用自己的均衡算法则要去掉该注解
    @LoadBalanced //开启负载均衡
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }
}
