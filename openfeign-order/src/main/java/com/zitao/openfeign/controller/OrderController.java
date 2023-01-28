package com.zitao.openfeign.controller;

import com.zitao.openfeign.feign.ProductFeignService;
import com.zitao.openfeign.feign.StockFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {
    //    @Autowired
//    private RestTemplate restTemplate;
    @Autowired
    private StockFeignService stockFeignService;

    @Autowired
    private ProductFeignService productFeignService;

    @RequestMapping("/add")
    @ResponseBody
    public String add() {
        System.out.println("下单成功");
//        String result = restTemplate.getForObject("http://stock-nacos:8001/stock/deduct", String.class);
        String result = stockFeignService.deduct();
        return "hello feign, spring cloud alibaba, result:" + result;
    }

    @RequestMapping("/getProduct")
    @ResponseBody
    public String getProduct() {
        String result = productFeignService.get(100);
        System.out.println("查询成功");
        return "hello feign, get product result:" + result;
    }

}
