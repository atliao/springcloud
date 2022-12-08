package com.la.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author LA
 * @createDate 2022-12-05-10:52
 */
public interface MyLoadBalance {

    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
