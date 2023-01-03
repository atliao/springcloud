package com.la.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author LA
 * @createDate 2023-01-03-18:10
 */
@Component
@EnableBinding(Sink.class) //可以理解为我们定义一个消息消费者的接收管道
@Slf4j
public class ReceiveMessageListener {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT) //输入源：作为一个消息监听者
    public void input(Message<String> message) {
        //获取到消息
        String messageStr = message.getPayload();
        log.info("消费者2号，serverport：" + serverPort + ", 接收到消息：" + messageStr);
    }
}
