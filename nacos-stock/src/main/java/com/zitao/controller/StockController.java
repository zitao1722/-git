package com.zitao.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {
    @RequestMapping("/deduct")
    @ResponseBody
    public String deduct() {
        System.out.println("扣减库存");
        return "减少库存成功";
    }
}
