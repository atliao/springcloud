package com.la.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author LA
 * @createDate 2022-11-30-22:43
 */
@SpringBootApplication
@EnableEurekaClient //表明自己是服务器，向注册中心注册信息
@EnableDiscoveryClient //可以获取注册中心上的微服务信息
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class, args);
    }
}
