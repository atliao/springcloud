package com.la.springcloud.controller;

import com.la.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LA
 * @createDate 2022-12-06-11:35
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "globalFallBackMethod")
public class OrderPaymentController {

    @Resource
    PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/hystrix/ok/{id}")
    public String consumer_info_ok(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/hystrix/timeout/{id}")
    //hystrxi处理，服务出问题时（包括超时和出错），转接到另一个兜底服务
    /*@HystrixCommand(fallbackMethod = "consumerInfo_TimeoutHandler", commandProperties = {
            //5秒内为正常，否则转向另一个服务
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })*/
    //@HystrixCommand//没有指明，则用全局的服务降级
    public String consumer_info_timeout(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        return result;
    }

    //兜底方法
    public String consumerInfo_TimeoutHandler(Integer id){
        return "来自consumer:" + " id: " + id + "\t" + "o(╥﹏╥)o对方系统繁忙，请稍后再试";
    }


    //下面是全局fallback，全局服务降级方法不能携带参数
    public String globalFallBackMethod(){
        return "Global异常处理信息，请稍后再试，~~o(>_<)o ~~";
    }
}
