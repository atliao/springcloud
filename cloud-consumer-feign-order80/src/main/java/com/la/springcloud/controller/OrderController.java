package com.la.springcloud.controller;

import com.la.springcloud.entities.CommonResult;
import com.la.springcloud.entities.Payment;
import com.la.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LA
 * @createDate 2022-12-05-11:51
 */
@RestController
@Slf4j
public class OrderController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/getFromPayment/get/{id}")
    public CommonResult<Payment> getFromPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/getFromPayment/timeout")
    public String getFromPaymentTimeout(){
        return paymentFeignService.paymentFeignTimeout();
    }
}
