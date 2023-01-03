package com.la.springboot.controller;

import com.la.springboot.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LA
 * @createDate 2022-12-11-20:43
 */
@RestController
public class SendMessageController {

    @Resource
    IMessageProvider iMessageProvider;

    @GetMapping("/stream/send")
    public String sendMessage(){
        return iMessageProvider.send();
    }
}
