package com.la.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.la.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import sun.nio.ch.DefaultSelectorProvider;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author LA
 * @createDate 2022-12-05-14:20
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${server.port}")
    private String serverport;

    private Integer timeoutCount = 0;

    /**
     * 正常访问
     * @param id
     * @return
     */
    @Override
    public String paymentInfo_OK(Integer id) {
        return serverport + ":" + "线程池： " + Thread.currentThread().getName() + " id: " + id + "\t" + "O(∩_∩)O哈哈~";
    }

    /**
     * 异常访问
     * @param id
     * @return
     */
    @Override
    //hystrxi处理，服务出问题时（包括超时和出错），转接到另一个兜底服务
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            //3秒内为正常，否则转向另一个服务
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_Timeout(Integer id) {
        timeoutCount++;
        System.out.println("*******************" + timeoutCount + "*******************");

        //int num = 10/0;

        int timeout = 3000;
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //int num = 10/0;

        return serverport + ":" + "线程池： " + Thread.currentThread().getName()
                + " id: " + id + "\t" + "O(∩_∩)O哈哈~ 耗时:" + timeout/1000 + "秒";
    }

    //******************************服务降级**********************************//
    //兜底方法
    public String paymentInfo_TimeoutHandler(Integer id){
        return "来自payment:" + "线程池： " + Thread.currentThread().getName()
                + " id: " + id + "\t" + "o(╥﹏╥)o服务器出问题啦，请稍后再试";
    }


    //******************************服务熔断**********************************//
    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "10000"), // 快照时间窗口，在该窗口时间内，失败次数达到一定数量，将会熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 使断路器跳闸的最小请求数量（默认是20），如果快照窗口时间内请求数小于设定值，就算请求全部失败也不会触发断路器。
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000") // 断路多久后尝试第一次恢复
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        if(id < 0)
        {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID(); //等价于UUID.randomUUID().toString()，且不带 "-" 号

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }
}
