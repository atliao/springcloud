package com.la.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author LA
 * @createDate 2022-12-03-19:44
 */
@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用consul或zookeeper作为注册中心时服务注册
public class OrderZKMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderZKMain80.class, args);
    }
}
