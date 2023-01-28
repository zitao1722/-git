package com.zitao.openfeign.feign;

import com.zitao.openfeign.config.OpenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "openfeign-product", path = "/product", configuration = OpenFeignConfig.class)
public interface ProductFeignService {
    // 在 OpenFeign 中使用 @PathVariable 等注解时，要手动指定参数名，否则可能出错
    @RequestMapping("/{id}")
    @ResponseBody
    String get(@PathVariable("id") Integer id);
}
