package com.zitao.openfeign.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Value("${server.port}")
    private String port;

    @RequestMapping("/{id}")
    @ResponseBody
    public String get(@PathVariable Integer id) {
        System.out.println("查询产品" + id);
        return "查询成功：id = " + id + ", port: " + port;
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        int a = 1 / 0;
        return "查询成功：a = " + a + ", port: " + port;
    }

    @RequestMapping("/testGateway")
    @ResponseBody
    public String testGateway() {
        return "Gateway网关调用成功" + ", port: " + port;
    }
}
