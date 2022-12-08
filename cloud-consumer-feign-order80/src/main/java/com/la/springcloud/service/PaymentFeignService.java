package com.la.springcloud.service;

import com.la.springcloud.entities.CommonResult;
import com.la.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author LA
 * @createDate 2022-12-05-11:51
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    //调用了cloud-provider-payment中controller的方法
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/timeout")
    public String paymentFeignTimeout();
}
