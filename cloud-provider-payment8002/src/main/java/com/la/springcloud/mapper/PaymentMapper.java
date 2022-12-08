package com.la.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.la.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author LA
 * @createDate 2022-11-30-21:32
 */
@Repository //将其放入容器中，可以解决自动注入报错问题
@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {

    int createPayment(Payment payment);

    Payment getPaymentById(@Param("id") Long id);


}
