package com.zitao.seata.feign;

import com.zitao.seata.config.OpenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 使用 @FeignClient 注解来使用 feign，添加feign接口和方法
 * name: 要调用的rest接口所对应服务的名称
 * path: 要调用的rest接口所在的controller路径，也即controller上 @RequestMapping注解中定义的路径，若无此注解，则不指定path
 */
//@FeignClient(name = "stock-nacos", path = "/stock", configuration = OpenFeignConfig.class)
public interface StockFeignService {
    // 声明需要调用的 rest 接口对应的方法
    // 直接将对应接口内容复制过来即可
    @RequestMapping("/deduct")
    @ResponseBody
    String deduct();
}

/**
 * @RestController
 * @RequestMapping("/stock") public class StockController {
 * @RequestMapping("/deduct")
 * @ResponseBody public String deduct() {
 * System.out.println("扣减库存");
 * return "减少库存成功";
 * }
 * }
 */