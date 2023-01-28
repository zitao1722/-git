package com.zitao.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import javax.naming.CannotProceedException;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigApplication.class, args);

        // 启动类启动时加载配置文件，可以从这里获取
        // 需要自定义一个 applicationContext
//        ConfigurableApplicationContext applicationContext = SpringApplication.run(NacosConfigApplication.class, args);
//        String userName = applicationContext.getEnvironment().getProperty("user.name");
//        String age = applicationContext.getEnvironment().getProperty("user.age");
//
//        System.out.println(userName + ", " + age);
    }
}
