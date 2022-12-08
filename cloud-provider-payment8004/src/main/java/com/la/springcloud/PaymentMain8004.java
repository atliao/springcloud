package com.la.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author LA
 * @createDate 2022-12-03-17:01
 */
@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用consul或zookeeper作为注册中心时服务注册
public class PaymentMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class, args);
    }
}
