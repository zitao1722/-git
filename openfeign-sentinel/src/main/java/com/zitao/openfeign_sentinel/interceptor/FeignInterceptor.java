package com.zitao.openfeign_sentinel.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeignInterceptor implements RequestInterceptor {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void apply(RequestTemplate template) {
        logger.info("feign 自定义拦截器，做你想做的事");
        template.header("aaa", "在请求头设置你想要的参数");
        template.query("id", "11111");
        template.uri("/11");
    }
}
