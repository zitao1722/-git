package com.zitao.sentinel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@RestController
@RequestMapping("/sentinel")
public class SentinelOrderController {
    @Value("${sentinel.name}")
    private String sentinelUser;

    @Value("${sentinel.password}")
    private String sentinelPassword;

    @RequestMapping("/get")
    @ResponseBody
    public String get() {
        System.out.println("获取配置");
        String result = sentinelUser + ", " + sentinelPassword;
        return "hello world, spring cloud alibaba sentinel, result:" + result;
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add() {
        System.out.println("添加");
        return "hello world, spring cloud alibaba sentinel, test sentinel: 关联流控模式";
    }
}
