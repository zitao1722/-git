package com.zitao.seata.controller;

import com.zitao.seata.entity.Order;
import com.zitao.seata.feign.ProductFeignService;
import com.zitao.seata.feign.SeataProductFeignService;
import com.zitao.seata.feign.StockFeignService;
import com.zitao.seata.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学习使用seata分布式事务
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/add/{productId}")
    @ResponseBody
    public String add(@PathVariable("productId") Integer productId) {
        int orderId = 11;  // 生成orderId
        Order order = Order.builder().id(orderId).productId(productId).status(101).build();

        // 添加订单，并调用远程服务减少库存
        int result = orderService.addOrderByParam(order);
        return result > 0 ? "下单成功" : "下单失败，请稍后重试";
    }

    @RequestMapping("/cancel/{orderId}")
    @ResponseBody
    public String cancelOrder(@PathVariable(value = "orderId") Integer orderId) {
        int result = orderService.cancelOrder(orderId);
        return result > 0 ? "取消订单成功" : "取消失败，请稍后重试";
    }

}
