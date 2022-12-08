package com.la.springcloud.controller;

import com.la.springcloud.entities.CommonResult;
import com.la.springcloud.entities.Payment;
import com.la.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author LA
 * @createDate 2022-11-30-20:58
 */
@RestController // 等价于@Controller + @ResponseBody
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    //为了方便负载均衡，要带上自己的端口号
    @Value("${server.port}")
    private String serverPort;

    //服务发现，可以注册自己的基本服务信息
    @Autowired
    private DiscoveryClient discoveryClient;

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
            return new CommonResult(460, "插入数据库失败" + serverPort, null);
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

    //服务发现功能实现
    @GetMapping("/payment/discovery")
    public Object discovery(){
        //从服务注册中心，获取服务列表清单
        List<String> services = discoveryClient.getServices();
        for(String service : services){
            log.info("**********service: " + service);
        }

        //根据微服务名称进一步获取微服务实例信息
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance : instances){
            log.info("**********instance: " + instance.getServiceId() + "\t" + instance.getHost()
                    + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return discoveryClient;
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
