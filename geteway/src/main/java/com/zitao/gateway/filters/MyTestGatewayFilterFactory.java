package com.zitao.gateway.filters;

import com.alibaba.nacos.common.utils.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.RedirectToGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * 局部过滤器
 */
@Component
public class MyTestGatewayFilterFactory extends AbstractGatewayFilterFactory<MyTestGatewayFilterFactory.Config> {
    // 声明静态常量，其值与Config类中的字段名一致，用于后面绑定属性
    public static final String VALUE_KEY = "value";

    // 这个构造函数一定要像下面这样写，不然会报错
    public MyTestGatewayFilterFactory() {
        super(Config.class);
    }


    @Override
    public List<String> shortcutFieldOrder() {
        // 绑定Config类中定义的属性，这样配置文件中就可以进行配置
        return Arrays.asList(VALUE_KEY);
    }


    @Override
    public GatewayFilter apply(MyTestGatewayFilterFactory.Config config) {
//        return (exchange, chain) -> {
//            // 使用自定义过滤器实现检查参数，请求中必须带有名为 "name" 的参数
//            // 1. 获取name参数
//            // 2. 如果 !=value 值（配置文件中配置的），则失败
//            String name = exchange.getRequest().getQueryParams().getFirst("name");  // 取得 name 参数的值
//            if (StringUtils.isNotBlank(name)) {
//                if (!config.getValue().equals(name)) {  // 判断从前端取得的值与配置文件中的值是否一致
//                    exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);  // 不一致返回404状态码
//                    return exchange.getResponse().setComplete();
//                }
//            }
//            // 一致则正常请求
//            return chain.filter(exchange);
//        };
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                // 获取请求参数name的值
                String name = exchange.getRequest().getQueryParams().getFirst("name");

                if (StringUtils.isNotBlank(name)) {
                    // config.getValue()为配置文件的值
                    if (config.getValue().equals(name)) {
                        //放行
                        return chain.filter(exchange);
                    } else {
                        //返回404
                        exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
                        return exchange.getResponse().setComplete();
                    }
                }
                // 正常请求
                return chain.filter(exchange);
            }
        };
    }

    public static class Config {

        String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
