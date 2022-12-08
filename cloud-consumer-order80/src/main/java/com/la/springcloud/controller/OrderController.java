package com.la.springcloud.controller;

import com.la.springcloud.entities.CommonResult;
import com.la.springcloud.entities.Payment;
import com.la.springcloud.lb.MyLoadBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author LA
 * @createDate 2022-11-30-23:20
 */
@RestController
public class OrderController {

    //可以从远程端口调用函数
    @Autowired
    RestTemplate restTemplate;

    @Resource
    private MyLoadBalance myLoadBalance;

    @Resource
    private DiscoveryClient discoveryClient;

    //单机版
    //public static final String PAYMENT_URL = "http://localhost:8001";
    //集群版，不写死，而是写微服务的名称
    //需要添加负载均衡功能，在配置文件config中
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment)
    {
        return restTemplate.postForObject(PAYMENT_URL +"/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id)
    {
        return restTemplate.getForObject(PAYMENT_URL +"/payment/get/"+id, CommonResult.class);
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id)
    {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            //如果成功，返回请求体中的内容
            return entity.getBody();
        }else{
            return new CommonResult<>(460, "操作失败");
        }
    }

    //自己写的轮询测试，需要去掉config类中的负载均衡@LoadBalance注解
    @GetMapping("/consumer/getPaymentLb")
    public String getPaymentLb(){
        //获取当前可用的服务
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances == null || instances.size() <= 0){
            return "no service";
        }
        //传入，获取一个服务
        ServiceInstance serviceInstance = myLoadBalance.instances(instances);

        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }


}
