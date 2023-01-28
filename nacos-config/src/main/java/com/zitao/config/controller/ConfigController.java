package com.zitao.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Value("${user1.name}")
    private String userName;

    @Value("${user1.age}")
    private String age;

    @RequestMapping("/get")
    public String get() {
        System.out.println(userName + ", " + age);
        return userName + ", " + age;
    }
}