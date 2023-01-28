package com.zitao.seata.controller;

import com.zitao.seata.entity.Product;
import com.zitao.seata.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ProductService productService;

    @RequestMapping("/{id}&{productId}")
    @ResponseBody
    public String get(@PathVariable("id") Integer id, @PathVariable("productId") Integer productId) {
        // 减少库存
        Product product = Product.builder().productId(productId).id(id).build();
        int result = productService.update(product);
        return result > 0 ? "更新库存成功" : "更新库存失败，请稍后重试";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {

        return "查询成功 " + ", port: " + port;
    }

    @RequestMapping("/add/{productId}")
    @ResponseBody
    public String add(@PathVariable(value = "productId") Integer productId) {
        Product product = Product.builder()
                .productId(productId)
                .build();
        int result = productService.incr(product);
        return result > 0 ? "更新库存成功" : "更新库存失败，请稍后重试";
    }
}
