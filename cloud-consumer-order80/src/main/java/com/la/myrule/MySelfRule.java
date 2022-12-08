package com.la.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LA
 * @createDate 2022-12-04-14:19
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        //替换为随机选取的规则
        return new RandomRule();
    }
}
