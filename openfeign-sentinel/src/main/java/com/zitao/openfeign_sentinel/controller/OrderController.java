package com.zitao.openfeign_sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zitao.openfeign_sentinel.feign.ProductFeignService;
import com.zitao.openfeign_sentinel.feign.StockFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {
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

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        String result = productFeignService.test();
        System.out.println("测试结果");
        return "hello feign, test product result:" + result;
    }


    @RequestMapping("/getById/{id}")
    @ResponseBody
    @SentinelResource(value = "getById", blockHandler = "hotBlockHandler")
    public String getById(@PathVariable("id") String id) {
        String result = "test 成功, id = " + id;
        return result;
    }

    public String hotBlockHandler(@PathVariable("id") String id, BlockException e) {
        return "热点参数限流";
    }
}
