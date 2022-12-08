package com.la.springcloud;

import com.la.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author LA
 * @createDate 2022-11-30-23:18
 */
@SpringBootApplication
@EnableEurekaClient //向注册中心注册自己
@EnableDiscoveryClient //可以获取服务中心的微服务
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MySelfRule.class) //替换Ribbon均衡方式
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
