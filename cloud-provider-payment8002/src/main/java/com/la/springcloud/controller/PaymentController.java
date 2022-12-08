package com.la.springcloud.controller;

import com.la.springcloud.entities.CommonResult;
import com.la.springcloud.entities.Payment;
import com.la.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author LA
 * @createDate 2022-12-01-13:28
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    //为了方便负载均衡，要带上自己的端口号
    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult createPayment(@RequestBody Payment payment) {
        //@RequestBody可以将传入的json数据，自动封装成对象类，但需要传入json数据
        //@RequestBody加上后就不可以直接在浏览器以拼接方式传过来，无法解析
        //int result = paymentService.createPayment(payment);
        boolean result = paymentService.save(payment); //测试mybatis-plus
        log.info("*****插入结果：" + result);

        if (result) {
            return new CommonResult(200, "插入数据库成功,serverPort: " + serverPort, true);
        } else {
            return new CommonResult(460, "插入数据库失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        //Payment payment = paymentService.getPaymentById(id);
        Payment payment = paymentService.getById(id);

        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort: " + serverPort, payment);
        } else {
            return new CommonResult(460, "没有对应记录,查询ID: " + id, null);
        }
    }

    //测试自己写的轮询算法
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB()
    {
        return serverPort;
    }

    //测试超时控制，故意暂停几秒钟
    @GetMapping("/payment/timeout")
    public String paymentFeignTimeout(){
        //暂停几秒钟
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}