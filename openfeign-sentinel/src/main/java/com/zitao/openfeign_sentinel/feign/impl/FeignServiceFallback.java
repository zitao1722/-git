package com.zitao.openfeign_sentinel.feign.impl;

import com.zitao.openfeign_sentinel.feign.ProductFeignService;
import org.springframework.stereotype.Component;

@Component
public class FeignServiceFallback implements ProductFeignService {
    @Override
    public String get(Integer id) {
        return "get 方法降级";
    }

    @Override
    public String test() {
        return "test 方法降级";
    }
}
