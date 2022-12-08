package com.la.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.la.springcloud.entities.Payment;

/**
 * @author LA
 * @createDate 2022-11-30-21:30
 */
public interface PaymentService extends IService<Payment> {

    int createPayment(Payment payment);

    Payment getPaymentById(Long id);
}
