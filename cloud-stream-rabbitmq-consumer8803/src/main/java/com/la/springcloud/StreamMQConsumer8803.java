package com.la.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author LA
 * @createDate 2023-01-03-18:09
 */

@SpringBootApplication
@EnableEurekaClient
public class StreamMQConsumer8803 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMQConsumer8803.class, args);
    }
}
