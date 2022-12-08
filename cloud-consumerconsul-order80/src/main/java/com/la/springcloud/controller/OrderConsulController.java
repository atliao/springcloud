package com.la.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author LA
 * @createDate 2022-12-04-11:27
 */
@RestController
@Slf4j
public class OrderConsulController {

    public static final String PAYMENT_URL = "http://cloud-provider-consul-payment";

    @Resource
    RestTemplate restTemplate;

    @GetMapping("/consumer/getfrompayment/consul")
    public String consumerPaymentConsul(){
        String result = restTemplate.getForObject(PAYMENT_URL + "/payment/consul", String.class);
        return result;
    }

}
