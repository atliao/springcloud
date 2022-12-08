package com.la.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author LA
 * @createDate 2022-11-30-22:59
 */
@SpringBootApplication
@EnableEurekaServer //表明自己是服务注册中心
public class EurekaMain7001{
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7001.class, args);
    }
}
