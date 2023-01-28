package com.zitao.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/add")
    @ResponseBody
    public String add() {
        System.out.println("下单成功");
        String result = restTemplate.getForObject("http://stock-nacos/stock/deduct", String.class);
        return "hello world, spring cloud alibaba, result:" + result;
    }
}
