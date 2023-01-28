package com.zitao.seata.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 全局配置
 * 使用@Configuration，会将此配置作用到所有的服务提供方
 * 如果只想针对某个服务进行局部配置，则不要加此注解
 * <p>
 * 局部配置，注释掉此注解
 */
@Configuration
public class OpenFeignConfig {
    // 注意：这个Logger是在包 feign 下的，不可使用其他的
    @Bean
    public Logger.Level feignLoggerLevel() {
        // 使用FULL级别的日志配置
        return Logger.Level.FULL;
    }

    /**
     * 超时时间配置
     */
//    @Bean
//    public Request.Options options() {
//        return new Request.Options(5000, 4000);
//    }

    /**
     * 配置类的方式
     * 配置feign的拦截器
     *
     * @return
     */
//    @Bean
//    public FeignInterceptor feignInterceptor() {
//        // 使用自定义的feign拦截器
//        return new FeignInterceptor();
//    }
}
