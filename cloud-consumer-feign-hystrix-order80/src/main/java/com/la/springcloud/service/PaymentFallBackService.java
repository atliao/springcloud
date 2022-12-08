package com.la.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author LA
 * @createDate 2022-12-06-15:22
 * 统一为接口方法实现服务降级
 */
@Component
public class PaymentFallBackService implements PaymentHystrixService{

    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----------paymentInfo_OK：服务器忙o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "-----------paymentInfo_Timeout：服务器忙o(╥﹏╥)o";
    }
}
