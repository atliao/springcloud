package com.la.springboot.service.impl;

import com.la.springboot.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author LA
 * @createDate 2022-12-11-20:26
 */
@EnableBinding(Source.class) //定义消息发送者的发送管道，消息生产者为source
@Slf4j
public class IMessageProviderImpl implements IMessageProvider {

    //这里不是操作数据库，是操作消息中间件，从中获取消息
    @Resource
    private MessageChannel output; //消息发送者管道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("************serial: " + serial);
        return "发送成功，serial: " + serial;
    }
}
