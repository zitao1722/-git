package com.zitao.dubbo3consumer.controller;

import com.zitao.dubbo3consumer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/getOrder")
    @ResponseBody
    public String getOrder(@RequestParam Integer orderId, @RequestParam Integer userId) {
        return orderService.getOrder(orderId, userId);
    }
}
