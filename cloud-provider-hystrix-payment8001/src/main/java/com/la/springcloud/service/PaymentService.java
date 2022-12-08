package com.la.springcloud.service;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author LA
 * @createDate 2022-12-05-14:19
 */
public interface PaymentService {

    String paymentInfo_OK(Integer id);

    String paymentInfo_Timeout(Integer id);

    String paymentCircuitBreaker(@PathVariable("id") Integer id);
}
