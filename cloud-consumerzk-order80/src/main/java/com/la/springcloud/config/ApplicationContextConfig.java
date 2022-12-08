package com.la.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author LA
 * @createDate 2022-12-03-19:46
 */
@Configuration
public class ApplicationContextConfig {


    //向容器中注入调用远程端口方法的对象，要先注入，才能使用
    @Bean
    @LoadBalanced//zookeeper注册，必须添加负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
