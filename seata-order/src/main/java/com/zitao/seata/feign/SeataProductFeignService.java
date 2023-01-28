package com.zitao.seata.feign;

import com.zitao.seata.config.OpenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "seata-product", path = "/product", configuration = OpenFeignConfig.class)
public interface SeataProductFeignService {
    /**
     * 在使用 openfeign 时，如果
     * 请求路径上有参数，使用 @PathVariable 注解
     * 而如果使用了 @PathVariable 注解，就必须要使用 value 来指定它的值，否则会出错
     *
     * @param id
     * @param productId
     * @return
     */
    @RequestMapping("/{id}&{productId}")
    @ResponseBody
    String get(@PathVariable(value = "id") Integer id, @PathVariable(value = "productId") Integer productId);

    @RequestMapping("/test")
    @ResponseBody
    String test();

    @RequestMapping("/add/{productId}")
    @ResponseBody
    public String add(@PathVariable(value = "productId") Integer productId);
}
