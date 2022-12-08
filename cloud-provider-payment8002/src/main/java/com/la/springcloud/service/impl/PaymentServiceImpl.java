package com.la.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.la.springcloud.entities.Payment;
import com.la.springcloud.mapper.PaymentMapper;
import com.la.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LA
 * @createDate 2022-11-30-21:30
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {

    @Autowired
    PaymentMapper paymentMapper;

    @Override
    public int createPayment(Payment payment) {
        return paymentMapper.createPayment(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentMapper.getPaymentById(id);
    }
}
